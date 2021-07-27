package fr.lavachequicode.services;

import fr.lavachequicode.lib.upnp.services.GroupControl;
import fr.lavachequicode.model.Group;
import fr.lavachequicode.model.GroupMember;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.fourthline.cling.model.meta.Device;
import org.fourthline.cling.model.types.UDN;
import org.fourthline.cling.registry.Registry;
import org.fourthline.cling.registry.event.RemoteDeviceDiscovery;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.ws.rs.NotFoundException;
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
    HeosUpnpFactoy heosUpnpFactory;

    @Getter
    Map<String, Group> groups = new HashMap<>();

    protected GroupControl getGroupControl(UDN udn) {
        final Device device = registry.getDevice(udn, false);
        if (device == null) {
            throw new NotFoundException();
        }
        return heosUpnpFactory.createProxy(device.findService(GroupControl.serviceId), GroupControl.class);
    }

    public HeosGroupService() {
        super();
    }

    void refresh(@Observes RemoteDeviceDiscovery event) {
        groups = registry.getDevices().stream()

                .map(device -> new AbstractMap.SimpleEntry<>(getGroupControl(device.getIdentity().getUdn()).getGroupUUID(), device))
                .map(
                        entry -> new AbstractMap.SimpleEntry<>(
                                entry.getKey(),
                                new GroupMember(
                                        entry.getValue().getIdentity().getUdn().getIdentifierString(),
                                        entry.getValue().getDetails().getFriendlyName(),
                                        getGroupControl(entry.getValue().getIdentity().getUdn()).getGroupStatus(entry.getKey())
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
                            if(group.getLeader() !=null){
                                group.setFriendlyName(getGroupControl(UDN.valueOf(group.getLeader().getUdn())).getGroupFriendlyName(entry.getKey()));
                            }
                        }
                ).collect(Collectors.toMap(Entry::getKey, Entry::getValue));

        groups.forEach((id, group) -> {

        });

        log.info("groups updated: {}", groups.size());
    }
}
