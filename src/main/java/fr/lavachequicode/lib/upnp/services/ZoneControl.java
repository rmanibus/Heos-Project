package fr.lavachequicode.lib.upnp.services;

import org.fourthline.cling.binding.annotations.UpnpService;
import org.fourthline.cling.binding.annotations.UpnpServiceId;
import org.fourthline.cling.binding.annotations.UpnpServiceType;
import org.fourthline.cling.model.types.ServiceId;
import org.fourthline.cling.model.types.UDAServiceId;

@UpnpService(
        serviceId = @UpnpServiceId(namespace = "denon-com", value = "ZoneControl"),
        serviceType = @UpnpServiceType(namespace = "schemas-denon-com", value = "ZoneControl", version = 1)
)
public interface ZoneControl {

    ServiceId serviceId = new ServiceId("denon-com", "ZoneControl");

}
