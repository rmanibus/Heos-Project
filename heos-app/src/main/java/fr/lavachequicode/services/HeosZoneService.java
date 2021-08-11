package fr.lavachequicode.services;

import fr.lavachequicode.heos.sdk.model.ZoneCurrentState;
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
  public Zone getZone(String id) {
    return buildZoneMap().get(id);
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
      Zone.builder()
        .id(entry.getKey())
        .friendlyName("No Zone")
        .leader(entry.getValue().stream()
          .map(this::extractLeader)
          .map(this::castToDevice)
          .filter(this::isZoneLeader)
          .findFirst()
          .orElse(null))
        .members(entry.getValue())
        .build()
    );
  }

  protected Entry<String, Member> memberToEntry(Member member) {
    if (member instanceof Group) {
      String zoneId = Optional.ofNullable(((Group) member).getLeader())
        .map(leader -> heosStateService.getDeviceZoneState(UDN.valueOf(leader.getId())).getZoneUUID().getValue())
        .filter(id -> !"".equals(id))
        .orElse(member.getId());
      return new SimpleEntry<>(zoneId, member);
    } else if (member instanceof Device) {
      String zoneId = Optional.of(heosStateService.getDeviceZoneState(UDN.valueOf((member).getId())).getZoneUUID().getValue())
        .filter(id -> !"".equals(id)).orElse(member.getId());
      ((Device) member).setZoneStatus(heosStateService.getDeviceZoneState(UDN.valueOf(member.getId())).getZoneStatus().getValue());
      return new SimpleEntry<>(zoneId, member);
    }
    return null;
  }

  protected void setZoneStatus(Member member) {
    if (member instanceof Group) {
      ((Group) member).getMembers().forEach(device -> device.setZoneStatus(heosStateService.getDeviceZoneState(UDN.valueOf(device.getId())).getZoneStatus().getValue()));
    } else if (member instanceof Device) {
      ((Device) member).setZoneStatus(heosStateService.getDeviceZoneState(UDN.valueOf(member.getId())).getZoneStatus().getValue());
    }
  }

  protected void fetchZoneFriendlyName(Zone zone) {
    Optional.ofNullable(zone.getLeader())
      .ifPresentOrElse(leader -> {
          final ZoneCurrentState leaderZoneState = heosStateService.getDeviceZoneState(UDN.valueOf(leader.getId()));
          zone.setFriendlyName(leaderZoneState.getZoneFriendlyName().getValue());
          zone.setVolume(Integer.valueOf(leaderZoneState.getZoneVolume().getValue()));
        },
        () -> {
          if (zone.getMembers().size() == 1) {
            Member member = zone.getMembers().get(0);
            zone.setFriendlyName(member.getFriendlyName());
            if (member instanceof Group) {
              zone.setLeader(((Group) member).getLeader());
              zone.setVolume(((Group) member).getVolume());
            } else {
              zone.setLeader((Device) member);
            }
          }
        });
  }

  protected Device castToDevice(Member member) {
    return (Device) member;
  }

  protected Member extractLeader(Member member) {
    if (member instanceof Group) {
      return ((Group) member).getMembers()
        .stream()
        .filter(this::isZoneLeader)
        .findFirst()
        .orElse(null);
    }
    return member;
  }

  protected boolean isZoneLeader(Device member) {
    return Optional.ofNullable(member)
      .map(Device::getZoneStatus)
      .map("ZONE_LEAD"::equals)
      .orElse(false);
  }
}
