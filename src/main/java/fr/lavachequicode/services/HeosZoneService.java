package fr.lavachequicode.services;

import fr.lavachequicode.model.Device;
import fr.lavachequicode.model.Group;
import fr.lavachequicode.model.Member;
import fr.lavachequicode.model.Zone;
import lombok.extern.slf4j.Slf4j;
import org.fourthline.cling.model.types.UDN;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.AbstractMap.SimpleEntry;
import java.util.*;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static fr.lavachequicode.model.Group.NO_GROUP_ID;

@ApplicationScoped
@Slf4j
public class HeosZoneService {

    @Inject
    HeosGroupService heosGroupService;
    @Inject
    HeosStateService heosStateService;

    public Collection<Zone> getZones() {
        return buildZoneMap().values();
    }


    Map<String, Zone> buildZoneMap() {

        final Map<String, Group> groupMap = heosGroupService.buildGroupMap();
        Group noGroup = groupMap.get(NO_GROUP_ID);
        return Stream.concat(
                groupMap.values().stream().filter(group -> !NO_GROUP_ID.equals(group.getId())),
                Optional.ofNullable(noGroup).map(Group::getMembers).stream().flatMap(Collection::stream)
        )
                .filter(Objects::nonNull)
                .peek(this::setZoneStatus)
                .map(this::memberToEntry)
                .filter(Objects::nonNull)
                .collect(Collectors.groupingBy(Entry::getKey, Collectors.mapping(Entry::getValue, Collectors.toList())))
                .entrySet().stream()
                .map(this::memberListEntryToZoneEntry)
                .peek(entry -> fetchZoneFriendlyName(entry.getValue()))
                .collect(Collectors.toUnmodifiableMap(Entry::getKey, Entry::getValue));
    }

    protected Entry<String, Zone> memberListEntryToZoneEntry(Entry<String, List<Member>> entry) {
        return new SimpleEntry<>(
                entry.getKey(),
                new Zone(
                        entry.getKey(),
                        "No Zone",
                        entry.getValue().stream()
                                .map(this::extractLeader)
                                .map(this::castToDevice)
                                .filter(this::isZoneLeader).findFirst().orElse(null),
                        entry.getValue()
                )
        );
    }

    protected Entry<String, Member> memberToEntry(Member member) {
        if (member instanceof Group) {
            return new SimpleEntry<>(heosStateService.getDeviceZoneState(UDN.valueOf(((Group) member).getLeader().getId())).getZoneUUID().getValue(), member);
        } else if (member instanceof Device) {
            ((Device) member).setZoneStatus(heosStateService.getDeviceZoneState(UDN.valueOf(member.getId())).getZoneStatus().getValue());
            return new SimpleEntry<>(heosStateService.getDeviceZoneState(UDN.valueOf((member).getId())).getZoneUUID().getValue(), member);
        }
        return null;
    }

    protected void setZoneStatus(Member member) {
        if (member instanceof Group) {
            ((Group) member).getMembers().forEach(device -> device.setZoneStatus(heosStateService.getDeviceZoneState(UDN.valueOf(device.getId())).getZoneStatus().getValue()));
            return;
        } else if (member instanceof Device) {
            ((Device) member).setZoneStatus(heosStateService.getDeviceZoneState(UDN.valueOf(member.getId())).getZoneStatus().getValue());
            return;
        }
        return;
    }

    protected void fetchZoneFriendlyName(Zone zone) {
        if (zone.getLeader() != null) {
            zone.setFriendlyName(heosStateService.getDeviceZoneState(UDN.valueOf(zone.getLeader().getId())).getZoneFriendlyName().getValue());
        }
    }

    protected Device castToDevice(Member member) {
        return (Device) member;
    }

    protected Member extractLeader(Member member) {
        if (member instanceof Group) {
            return ((Group) member).getMembers().stream().filter(this::isZoneLeader).findFirst().orElse(null);
        }
        return member;
    }

    protected boolean isZoneLeader(Device member) {
        return "ZONE_LEAD".equals((member).getZoneStatus());
    }
}
