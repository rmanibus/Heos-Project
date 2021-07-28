package fr.lavachequicode.lib.upnp.devices;

import fr.lavachequicode.lib.upnp.services.ErrorHandler;
import fr.lavachequicode.lib.upnp.services.GroupControl;
import fr.lavachequicode.lib.upnp.services.ZoneControl;
import org.fourthline.cling.model.types.DeviceType;

public class AiosServices {
    public static final  DeviceType type = new DeviceType("schemas-denon-com", "AiosServices");
    public static final  Class<?>[] services = {GroupControl.class, ZoneControl.class, ErrorHandler.class};
}
