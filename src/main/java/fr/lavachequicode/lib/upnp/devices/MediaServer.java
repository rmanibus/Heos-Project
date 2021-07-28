package fr.lavachequicode.lib.upnp.devices;

import fr.lavachequicode.lib.upnp.services.ConnectionManager;
import fr.lavachequicode.lib.upnp.services.ContentDirectory;
import org.fourthline.cling.model.types.DeviceType;
import org.fourthline.cling.model.types.UDADeviceType;

public class MediaServer {
    public static final  DeviceType type = new UDADeviceType("MediaServer");
    public static final  Class<?>[] services = {ContentDirectory.class, ConnectionManager.class};
}
