package fr.lavachequicode.services;

import fr.lavachequicode.lib.upnp.devices.AiosDevice;
import fr.lavachequicode.model.Group;
import fr.lavachequicode.model.GroupMember;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.fourthline.cling.model.types.UDN;
import org.fourthline.cling.registry.Registry;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.*;
import java.util.AbstractMap.SimpleEntry;
import java.util.Map.Entry;
import java.util.stream.Collectors;

@ApplicationScoped
@Slf4j
public class HeosGroupService {

    @Inject
    Registry registry;
    @Inject
    HeosStateService heosStateService;

    public Collection<Group> getGroups(){
        return buildGroupMap().values();
    }

    public Group getGroup(String id){
        return buildGroupMap().get(id);
    }

    Map<String, Group> buildGroupMap() {

        return registry.getDevices(AiosDevice.type).stream()
                .filter(device -> heosStateService.containsGroupStateForDevice(device.getIdentity().getUdn()))
                .map(device -> new SimpleEntry<>(heosStateService.getDeviceGroupState(device.getIdentity().getUdn()).getGroupUUID().getValue(), device))
                .map(
                        entry -> new SimpleEntry<>(
                                entry.getKey(),
                                new GroupMember(
                                        entry.getValue().getIdentity().getUdn().getIdentifierString(),
                                        entry.getValue().getDetails().getFriendlyName(),
                                        heosStateService.getDeviceGroupState(entry.getValue().getIdentity().getUdn()).getGroupStatus().getValue()
                                )
                        )
                )
                .collect(Collectors.groupingBy(Entry::getKey, Collectors.mapping(Entry::getValue, Collectors.toList())))
                .entrySet().stream()
                .map(
                        entry -> new SimpleEntry<>(
                                entry.getKey(),
                                new Group(
                                        entry.getKey(),
                                        "No Group",
                                        entry.getValue().stream().filter(member -> "GROUP_LEAD".equals(member.getStatus())).findFirst().orElse(null),
                                        entry.getValue()
                                )
                        )
                ).peek(
                        entry -> {
                            Group group = entry.getValue();
                            if (group.getLeader() != null) {
                                group.setFriendlyName(heosStateService.getDeviceGroupState(UDN.valueOf(group.getLeader().getUdn())).getGroupFriendlyName().getValue());
                            }
                        }
                ).collect(Collectors.toUnmodifiableMap(Entry::getKey, Entry::getValue));
    }
}
