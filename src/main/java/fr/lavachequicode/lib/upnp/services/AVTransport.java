package fr.lavachequicode.lib.upnp.services;

import fr.lavachequicode.lib.upnp.model.AVTCurrentState;
import org.fourthline.cling.binding.annotations.*;
import org.fourthline.cling.model.types.ServiceId;
import org.fourthline.cling.model.types.UDAServiceId;
import org.fourthline.cling.model.types.UnsignedIntegerFourBytes;

@UpnpService(
        serviceId = @UpnpServiceId("AVTransport"),
        serviceType = @UpnpServiceType(value = "AVTransport", version = 1)
)
public interface AVTransport {

    ServiceId serviceId = new UDAServiceId("AVTransport");

    @UpnpAction(name = "Stop")
    public abstract void stop(@UpnpInputArgument(name = "InstanceID") String instanceId);

    @UpnpAction(name = "Play")
    void play(
            @UpnpInputArgument(name = "InstanceID") String instanceId,
            @UpnpInputArgument(name = "Speed") String speed);

    @UpnpAction(name = "Pause")
    void pause(@UpnpInputArgument(name = "InstanceID") String instanceId);

    @UpnpAction(name = "Record")
    void record(@UpnpInputArgument(name = "InstanceID") String instanceId);

    @UpnpAction(name = "Seek")
    void seek(@UpnpInputArgument(name = "InstanceID") String instanceId,
              @UpnpInputArgument(name = "Unit", stateVariable = "A_ARG_TYPE_SeekMode") String unit,
              @UpnpInputArgument(name = "Target", stateVariable = "A_ARG_TYPE_SeekTarget") String target);

    @UpnpAction(name = "Next")
    void next(@UpnpInputArgument(name = "InstanceID") String instanceId);

    @UpnpAction(name = "Previous")
    void previous(@UpnpInputArgument(name = "InstanceID") String instanceId);

    @UpnpAction(name = "SetPlayMode")
    void setPlayMode(@UpnpInputArgument(name = "InstanceID") String instanceId,
                     @UpnpInputArgument(name = "NewPlayMode", stateVariable = "CurrentPlayMode") String newPlayMode);

    @XML
    @UpnpAction(name = "GetCurrentState", out = @UpnpOutputArgument(name = "CurrentState"))
    AVTCurrentState getCurrentState();

    @UpnpAction(name = "GetCurrentTransportActions", out = @UpnpOutputArgument(name = "Actions", stateVariable = "CurrentTransportActions"))
    String getCurrentTransportActions(@UpnpInputArgument(name = "InstanceID") String instanceId);

}
