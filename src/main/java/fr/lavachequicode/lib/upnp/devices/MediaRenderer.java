package fr.lavachequicode.lib.upnp.devices;

import fr.lavachequicode.lib.upnp.services.AVTransport;
import fr.lavachequicode.lib.upnp.services.ConnectionManager;
import fr.lavachequicode.lib.upnp.services.RenderingControl;
import org.fourthline.cling.model.types.DeviceType;
import org.fourthline.cling.model.types.UDADeviceType;

public class MediaRenderer {
    public static final  DeviceType type = new UDADeviceType("MediaRenderer");
    public static final  Class<?>[] services = {ConnectionManager.class, AVTransport.class, RenderingControl.class};
}
