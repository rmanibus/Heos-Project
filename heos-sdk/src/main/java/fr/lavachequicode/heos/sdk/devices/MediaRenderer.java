package fr.lavachequicode.heos.sdk.devices;

import fr.lavachequicode.heos.sdk.services.AVTransport;
import fr.lavachequicode.heos.sdk.services.ConnectionManager;
import fr.lavachequicode.heos.sdk.services.RenderingControl;
import org.fourthline.cling.model.types.DeviceType;
import org.fourthline.cling.model.types.UDADeviceType;

public class MediaRenderer {
    public static final  DeviceType type = new UDADeviceType("MediaRenderer");
    public static final  Class<?>[] services = {ConnectionManager.class, AVTransport.class, RenderingControl.class};
}
