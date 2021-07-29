package fr.lavachequicode.heos.sdk.devices;

import org.fourthline.cling.model.types.DeviceType;

public class AiosDevice {
    public static final DeviceType type = new DeviceType("schemas-denon-com", "AiosDevice");
    public static final  DeviceType[] embededDeviceTypes = {ACTDenon.type, AiosDevice.type, MediaRenderer.type, MediaServer.type};
}
