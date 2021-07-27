package fr.lavachequicode.lib.upnp.services;

import org.fourthline.cling.binding.annotations.UpnpService;
import org.fourthline.cling.binding.annotations.UpnpServiceId;
import org.fourthline.cling.binding.annotations.UpnpServiceType;
import org.fourthline.cling.model.types.ServiceId;
import org.fourthline.cling.model.types.UDAServiceId;

@UpnpService(
        serviceId = @UpnpServiceId("ConnectionManager"),
        serviceType = @UpnpServiceType(value = "ConnectionManager", version = 1)
)
public interface ConnectionManager {

    ServiceId serviceId = new UDAServiceId("ConnectionManager");

}
