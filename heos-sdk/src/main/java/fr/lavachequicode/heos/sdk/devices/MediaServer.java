package fr.lavachequicode.heos.sdk.devices;

import fr.lavachequicode.heos.sdk.services.ConnectionManager;
import fr.lavachequicode.heos.sdk.services.ContentDirectory;
import org.fourthline.cling.model.types.DeviceType;
import org.fourthline.cling.model.types.UDADeviceType;

public class MediaServer {
    public static final  DeviceType type = new UDADeviceType("MediaServer");
    public static final  Class<?>[] services = {ContentDirectory.class, ConnectionManager.class};
}
