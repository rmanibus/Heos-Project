package fr.lavachequicode.heos.sdk.services;

import org.fourthline.cling.binding.annotations.*;
import org.fourthline.cling.model.types.ServiceId;

@UpnpService(
        serviceId = @UpnpServiceId(namespace = "denon-com", value = "ErrorHandler"),
        serviceType = @UpnpServiceType(namespace = "schemas-denon-com", value = "ErrorHandler", version = 1)
)
public interface ErrorHandler {

    ServiceId serviceId = new ServiceId("denon-com", "ErrorHandler");

    @UpnpAction(name = "ClearError")
    String clearError(@UpnpInputArgument(name = "ErrorID") String errorID);

}
