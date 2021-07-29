package fr.lavachequicode.services;

import fr.lavachequicode.heos.sdk.devices.AiosDevice;
import fr.lavachequicode.heos.sdk.model.GroupCurrentState;
import fr.lavachequicode.heos.sdk.model.ZoneCurrentState;
import fr.lavachequicode.model.Device;
import org.fourthline.cling.registry.Registry;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Collection;
import java.util.stream.Collectors;

@ApplicationScoped
public class HeosDeviceService {

  @Inject
  Registry registry;
  @Inject
  HeosStateService heosStateService;

  public Collection<Device> getDevices() {
    return registry.getDevices(AiosDevice.type).stream()
      .filter(this::stateExist)
      .map(this::createDevice)
      .peek(this::fetchGroupData)
      .peek(this::fetchZoneData)
      .collect(Collectors.toList());
  }

  protected boolean stateExist(org.fourthline.cling.model.meta.Device device) {
    return heosStateService.containsGroupStateForDevice(device.getIdentity().getUdn());
  }

  protected Device createDevice(org.fourthline.cling.model.meta.Device device) {
    return Device.builder().id(device.getIdentity().getUdn().getIdentifierString()).build();
  }

  protected void fetchGroupData(Device device) {
    GroupCurrentState deviceGroupState = heosStateService.getDeviceGroupState(device.getUdn());
    device.setFriendlyName(deviceGroupState.getDeviceFriendlyName().getValue());
    device.setGroupStatus(deviceGroupState.getGroupStatus().getValue());
    device.setAudioChannel(deviceGroupState.getAudioChannel().getValue());
  }

  protected void fetchZoneData(Device device) {
    ZoneCurrentState deviceZoneState = heosStateService.getDeviceZoneState(device.getUdn());
    device.setZoneStatus(deviceZoneState.getZoneStatus().getValue());
  }
}
