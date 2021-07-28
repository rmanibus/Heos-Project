package fr.lavachequicode.services;

import fr.lavachequicode.lib.upnp.devices.AiosDevice;
import fr.lavachequicode.model.Device;
import fr.lavachequicode.model.Group;
import lombok.extern.slf4j.Slf4j;
import org.fourthline.cling.model.types.UDN;
import org.fourthline.cling.registry.Registry;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.AbstractMap.SimpleEntry;
import java.util.Collection;
import java.util.List;
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

    public Collection<Group> getGroups() {
        return buildGroupMap().values();
    }

    public Group getGroup(String id) {
        return buildGroupMap().get(id);
    }

    Map<String, Group> buildGroupMap() {

        return registry.getDevices(AiosDevice.type).stream()
                .filter(device -> heosStateService.containsGroupStateForDevice(device.getIdentity().getUdn()))
                .map(this::deviceToEntry)
                .map(this::deviceEntryToModel)
                .collect(Collectors.groupingBy(Entry::getKey, Collectors.mapping(Entry::getValue, Collectors.toList())))
                .entrySet().stream()
                .map(this::devicesToGroup)
                .peek(entry -> fetchFriendlyName(entry.getValue()))
                .collect(Collectors.toUnmodifiableMap(Entry::getKey, Entry::getValue));
    }

    protected void fetchFriendlyName(Group group) {
        if (group.getLeader() != null) {
            group.setFriendlyName(heosStateService.getDeviceGroupState(UDN.valueOf(group.getLeader().getId())).getGroupFriendlyName().getValue());
        }
    }

    protected Entry<String, Group> devicesToGroup(Entry<String, List<Device>> entry) {
        return new SimpleEntry<>(
                entry.getKey(),
                Group.builder()
                        .id(entry.getKey())
                        .friendlyName("No Group")
                        .leader(entry.getValue().stream().filter(member -> "GROUP_LEAD".equals(member.getGroupStatus())).findFirst().orElse(null))
                        .members(entry.getValue()).build()
        );
    }

    protected Entry<String, org.fourthline.cling.model.meta.Device> deviceToEntry(org.fourthline.cling.model.meta.Device device) {
        return new SimpleEntry<>(heosStateService.getDeviceGroupState(device.getIdentity().getUdn()).getGroupUUID().getValue(), device);
    }

    protected Entry<String, Device> deviceEntryToModel(Entry<String, org.fourthline.cling.model.meta.Device> entry) {
        return new SimpleEntry<>(
                entry.getKey(),
                Device.builder()
                        .id(entry.getValue().getIdentity().getUdn().getIdentifierString())
                        .friendlyName(entry.getValue().getDetails().getFriendlyName())
                        .groupStatus(heosStateService.getDeviceGroupState(entry.getValue().getIdentity().getUdn()).getGroupStatus().getValue())
                        .audioChannel(heosStateService.getDeviceGroupState(entry.getValue().getIdentity().getUdn()).getAudioChannel().getValue())
                        .build()
        );
    }


}
