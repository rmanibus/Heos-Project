package fr.lavachequicode.services;

import fr.lavachequicode.heos.sdk.devices.AiosDevice;
import fr.lavachequicode.heos.sdk.model.GroupCurrentState;
import fr.lavachequicode.heos.sdk.model.ZoneCurrentState;
import fr.lavachequicode.model.Device;
import org.fourthline.cling.model.types.UDN;
import org.fourthline.cling.registry.Registry;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Collection;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;

@ApplicationScoped
public class HeosDeviceService {

  @Inject
  Registry registry;
  @Inject
  HeosStateService heosStateService;

  public Device getDevice(String udn) {
    return Optional.ofNullable(registry.getDevice(UDN.valueOf(udn), true))
      .filter(this::stateExist)
      .map(this::createDevice)
      .map(peek(this::fetchGroupData))
      .map(peek(this::fetchZoneData))
      .orElse(null);
  }

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

  protected <T> UnaryOperator<T> peek(Consumer<T> c) {
    return x -> {
      c.accept(x);
      return x;
    };
  }
}
