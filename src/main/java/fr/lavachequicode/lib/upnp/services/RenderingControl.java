package fr.lavachequicode.lib.upnp.services;

import org.fourthline.cling.binding.annotations.*;
import org.fourthline.cling.model.types.ServiceId;
import org.fourthline.cling.model.types.UDAServiceId;
import org.fourthline.cling.model.types.UnsignedIntegerFourBytes;
import org.fourthline.cling.model.types.UnsignedIntegerTwoBytes;

@UpnpService(
        serviceId = @UpnpServiceId("RenderingControl"),
        serviceType = @UpnpServiceType(value = "RenderingControl", version = 1)
)
public interface RenderingControl {

    ServiceId serviceId = new UDAServiceId("RenderingControl");

    @UpnpAction(out = @UpnpOutputArgument(name = "CurrentPresetNameList", stateVariable = "PresetNameList"))
    String listPresets(@UpnpInputArgument(name = "InstanceID") UnsignedIntegerFourBytes instanceId);

    @UpnpAction
    void selectPreset(@UpnpInputArgument(name = "InstanceID") UnsignedIntegerFourBytes instanceId,
                      @UpnpInputArgument(name = "PresetName") String presetName);

    @UpnpAction(out = @UpnpOutputArgument(name = "CurrentMute", stateVariable = "Mute"))
    abstract boolean getMute(@UpnpInputArgument(name = "InstanceID") UnsignedIntegerFourBytes instanceId,
                             @UpnpInputArgument(name = "Channel") String channelName);

    @UpnpAction
    void setMute(@UpnpInputArgument(name = "InstanceID") UnsignedIntegerFourBytes instanceId,
                 @UpnpInputArgument(name = "Channel") String channelName,
                 @UpnpInputArgument(name = "DesiredMute", stateVariable = "Mute") boolean desiredMute);

    @UpnpAction(out = @UpnpOutputArgument(name = "CurrentVolume", stateVariable = "Volume"))
    UnsignedIntegerTwoBytes getVolume(@UpnpInputArgument(name = "InstanceID") UnsignedIntegerFourBytes instanceId,
                                      @UpnpInputArgument(name = "Channel") String channelName);

    @UpnpAction
    void setVolume(@UpnpInputArgument(name = "InstanceID") UnsignedIntegerFourBytes instanceId,
                   @UpnpInputArgument(name = "Channel") String channelName,
                   @UpnpInputArgument(name = "DesiredVolume", stateVariable = "Volume") UnsignedIntegerTwoBytes desiredVolume);

    @UpnpAction(out = @UpnpOutputArgument(name = "CurrentVolume", stateVariable = "VolumeDB"))
    Integer getVolumeDB(@UpnpInputArgument(name = "InstanceID") UnsignedIntegerFourBytes instanceId,
                        @UpnpInputArgument(name = "Channel") String channelName);

    @UpnpAction
    void setVolumeDB(@UpnpInputArgument(name = "InstanceID") UnsignedIntegerFourBytes instanceId,
                     @UpnpInputArgument(name = "Channel") String channelName,
                     @UpnpInputArgument(name = "DesiredVolume", stateVariable = "VolumeDB") Integer desiredVolumeDB);

    @UpnpAction(out = {
            @UpnpOutputArgument(name = "MinValue", stateVariable = "VolumeDB", getterName = "getMinValue"),
            @UpnpOutputArgument(name = "MaxValue", stateVariable = "VolumeDB", getterName = "getMaxValue")
    })
    String getVolumeDBRange(@UpnpInputArgument(name = "InstanceID") UnsignedIntegerFourBytes instanceId,
                            @UpnpInputArgument(name = "Channel") String channelName);

    @UpnpAction(out = @UpnpOutputArgument(name = "CurrentLoudness", stateVariable = "Loudness"))
    boolean getLoudness(@UpnpInputArgument(name = "InstanceID") UnsignedIntegerFourBytes instanceId,
                        @UpnpInputArgument(name = "Channel") String channelName);

    @UpnpAction
    void setLoudness(@UpnpInputArgument(name = "InstanceID") UnsignedIntegerFourBytes instanceId,
                     @UpnpInputArgument(name = "Channel") String channelName,
                     @UpnpInputArgument(name = "DesiredLoudness", stateVariable = "Loudness") boolean desiredLoudness);
}
