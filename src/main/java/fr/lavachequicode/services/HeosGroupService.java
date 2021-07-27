package fr.lavachequicode.services;

import fr.lavachequicode.events.StateUpdated;
import fr.lavachequicode.model.Group;
import fr.lavachequicode.model.GroupMember;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.fourthline.cling.model.types.UDN;
import org.fourthline.cling.registry.Registry;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

@ApplicationScoped
@Slf4j
public class HeosGroupService {

    @Inject
    Registry registry;
    @Inject
    HeosStateService heosStateService;
    @Getter
    Map<String, Group> groups = new HashMap<>();

    public HeosGroupService() {
        super();
    }

    void refresh(@Observes StateUpdated event) {
        groups = heosStateService.getGroupStateDevicesIds().stream()
                .map(udn -> registry.getDevice(UDN.valueOf(udn), true))
                .map(device -> new AbstractMap.SimpleEntry<>(heosStateService.getDeviceGroupState(device.getIdentity().getUdn()).getGroupUUID().getValue(), device))
                .map(
                        entry -> new AbstractMap.SimpleEntry<>(
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
                        entry -> new AbstractMap.SimpleEntry<>(
                                entry.getKey(),
                                new Group(
                                        entry.getKey(),
                                        "Fetching ...",
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

        groups.forEach((id, group) -> {

        });
        log.info("groups updated! size: {}", groups.size());
    }
}
