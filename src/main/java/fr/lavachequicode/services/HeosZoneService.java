package fr.lavachequicode.services;

import fr.lavachequicode.model.Device;
import fr.lavachequicode.model.Group;
import fr.lavachequicode.model.Zone;
import lombok.extern.slf4j.Slf4j;
import org.fourthline.cling.model.types.UDN;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.AbstractMap.SimpleEntry;
import java.util.Collection;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
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
                .map(member -> {
                    log.info("member {}", member);

                    if (member instanceof Group) {
                        ((Group) member).getMembers().forEach(device -> device.setZoneStatus(heosStateService.getDeviceZoneState(UDN.valueOf(device.getId())).getZoneStatus().getValue()));
                        return new SimpleEntry<>(heosStateService.getDeviceZoneState(UDN.valueOf(((Group) member).getLeader().getId())).getZoneUUID().getValue(), member);
                    } else if (member instanceof Device) {
                        ((Device) member).setZoneStatus(heosStateService.getDeviceZoneState(UDN.valueOf(member.getId())).getZoneStatus().getValue());
                        return new SimpleEntry<>(heosStateService.getDeviceZoneState(UDN.valueOf((member).getId())).getZoneUUID().getValue(), member);
                    }
                    return null;
                })
                .filter(Objects::nonNull)
                .collect(Collectors.groupingBy(Map.Entry::getKey, Collectors.mapping(Map.Entry::getValue, Collectors.toList())))
                .entrySet().stream().map(
                        entry -> new SimpleEntry<>(
                                entry.getKey(),
                                new Zone(
                                        entry.getKey(),
                                        "No Zone",
                                        null,
                                        entry.getValue()
                                )
                        )
                )
                .peek(
                        entry -> {
                            Zone zone = entry.getValue();
                            if (zone.getLeader() != null) {
                                zone.setFriendlyName(heosStateService.getDeviceZoneState(UDN.valueOf(zone.getLeader().getId())).getZoneFriendlyName().getValue());
                            }
                        }
                )
                .collect(Collectors.toUnmodifiableMap(Map.Entry::getKey, Map.Entry::getValue));
    }
}
