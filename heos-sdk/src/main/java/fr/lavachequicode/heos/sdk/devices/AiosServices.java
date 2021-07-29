package fr.lavachequicode.heos.sdk.devices;

import fr.lavachequicode.heos.sdk.services.ErrorHandler;
import fr.lavachequicode.heos.sdk.services.GroupControl;
import fr.lavachequicode.heos.sdk.services.ZoneControl;
import org.fourthline.cling.model.types.DeviceType;

public class AiosServices {
    public static final  DeviceType type = new DeviceType("schemas-denon-com", "AiosServices");
    public static final  Class<?>[] services = {GroupControl.class, ZoneControl.class, ErrorHandler.class};
}
