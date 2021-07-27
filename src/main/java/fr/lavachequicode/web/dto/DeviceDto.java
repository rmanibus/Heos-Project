package fr.lavachequicode.web.dto;

import lombok.Data;
import org.fourthline.cling.model.meta.*;
import org.fourthline.cling.model.types.DeviceType;

import java.util.Arrays;

@Data
public class DeviceDto {
    final private DeviceIdentity deviceIdentity;
    final private UDAVersion version;
    final private DeviceType type;
    final private DeviceDetails details;
    final private Icon[] icons;
    final protected ServiceDto[] services;
    final protected DeviceDto[] embeddedDevices;

    public DeviceDto(Device device) {
        this.deviceIdentity = device.getIdentity();
        this.version = device.getVersion();
        this.type = device.getType();
        this.details = device.getDetails();
        this.icons = device.getIcons();
        this.services = Arrays.stream(device.getServices()).map(ServiceDto::new).toArray(ServiceDto[]::new);
        this.embeddedDevices = Arrays.stream(device.getEmbeddedDevices()).map(DeviceDto::new).toArray(DeviceDto[]::new);
    }
}
