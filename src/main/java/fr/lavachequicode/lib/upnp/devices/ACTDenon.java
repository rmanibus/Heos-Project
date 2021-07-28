package fr.lavachequicode.lib.upnp.devices;

import fr.lavachequicode.lib.upnp.services.ACT;
import org.fourthline.cling.model.types.DeviceType;

public class ACTDenon {
    public static final  DeviceType type = new DeviceType("schemas-denon-com", "ACT-Denon");
    public static final  Class<?>[] services = {ACT.class};
}
