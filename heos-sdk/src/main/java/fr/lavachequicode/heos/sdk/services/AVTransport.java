package fr.lavachequicode.heos.sdk.services;

import fr.lavachequicode.heos.sdk.model.AVTCurrentState;
import fr.lavachequicode.heos.sdk.model.PositionInfo;
import org.fourthline.cling.binding.annotations.*;
import org.fourthline.cling.model.types.ServiceId;
import org.fourthline.cling.model.types.UDAServiceId;

@UpnpService(
        serviceId = @UpnpServiceId("AVTransport"),
        serviceType = @UpnpServiceType(value = "AVTransport", version = 1)
)
public interface AVTransport {

    ServiceId serviceId = new UDAServiceId("AVTransport");

    @UpnpAction(name = "Stop")
    void stop(@UpnpInputArgument(name = "InstanceID") String instanceId);

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

    @UpnpAction(name = "SetNextAVTransportURI")
    void setNextAVTransportURI(@UpnpInputArgument(name = "InstanceID") String instanceId,
                               @UpnpInputArgument(name = "NextURI", stateVariable = "NextAVTransportURI") String nextURI,
                               @UpnpInputArgument(name = "NextURIMetaData", stateVariable = "NextAVTransportURIMetaData") String nextURIMetaData);

    @UpnpAction(name = "SetAVTransportURI")
    void setAVTransportURI(@UpnpInputArgument(name = "InstanceID") String instanceId,
                               @UpnpInputArgument(name = "CurrentURI", stateVariable = "AVTransportURI") String currentURI,
                               @UpnpInputArgument(name = "CurrentURIMetaData", stateVariable = "AVTransportURIMetaData") String currentURIMetaData);


    @UpnpAction(name = "GetCurrentState", out = @UpnpOutputArgument(name = "CurrentState"))
    AVTCurrentState getCurrentState();

    @UpnpAction(name = "GetDeviceCapabilities", out = {
            @UpnpOutputArgument(name = "PlayMedia"),
            @UpnpOutputArgument(name = "RecMedia"),
            @UpnpOutputArgument(name = "RecQualityModes"),
    })
    String getDeviceCapabilities(@UpnpInputArgument(name = "InstanceID") String instanceId);

    @UpnpAction(name = "GetTransportInfo", out = {
            @UpnpOutputArgument(name = "CurrentTransportState"),
            @UpnpOutputArgument(name = "CurrentTransportStatus"),
            @UpnpOutputArgument(name = "CurrentSpeed"),
    })
    String getTransportInfo(@UpnpInputArgument(name = "InstanceID") String instanceId);

    @UpnpAction(name = "GetTransportSettings", out = {
            @UpnpOutputArgument(name = "PlayMode"),
            @UpnpOutputArgument(name = "RecQualityMode"),
    })
    String getTransportSettings(@UpnpInputArgument(name = "InstanceID") String instanceId);


    @UpnpAction(name = "GetPositionInfo", out = {
            @UpnpOutputArgument(name = "Track"),
            @UpnpOutputArgument(name = "TrackDuration"),
            @UpnpOutputArgument(name = "TrackMetaData"),
            @UpnpOutputArgument(name = "TrackURI"),
            @UpnpOutputArgument(name = "RelTime"),
            @UpnpOutputArgument(name = "AbsTime"),
            @UpnpOutputArgument(name = "RelativeCounterPosition"),
            @UpnpOutputArgument(name = "AbsCount"),
    })
    PositionInfo getPositionInfo(@UpnpInputArgument(name = "InstanceID") String instanceId);

    @UpnpAction(name = "GetMediaInfo", out = {
            @UpnpOutputArgument(name = "NrTracks"),
            @UpnpOutputArgument(name = "MediaDuration"),
            @UpnpOutputArgument(name = "CurrentURI"),
            @UpnpOutputArgument(name = "CurrentURIMetaData"),
            @UpnpOutputArgument(name = "NextURI"),
            @UpnpOutputArgument(name = "NextURIMetaData"),
            @UpnpOutputArgument(name = "PlayMedium"),
            @UpnpOutputArgument(name = "RecordMedium"),
            @UpnpOutputArgument(name = "WriteStatus"),
    })
    String getMediaInfo(@UpnpInputArgument(name = "InstanceID") String instanceId);

    @UpnpAction(name = "GetMediaInfo_Ext", out = {
            @UpnpOutputArgument(name = "CurrentType"),
            @UpnpOutputArgument(name = "NrTracks"),
            @UpnpOutputArgument(name = "MediaDuration"),
            @UpnpOutputArgument(name = "CurrentURI"),
            @UpnpOutputArgument(name = "CurrentURIMetaData"),
            @UpnpOutputArgument(name = "NextURI"),
            @UpnpOutputArgument(name = "NextURIMetaData"),
            @UpnpOutputArgument(name = "PlayMedium"),
            @UpnpOutputArgument(name = "RecordMedium"),
            @UpnpOutputArgument(name = "WriteStatus"),
    })
    String getMediaInfo_Ext(@UpnpInputArgument(name = "InstanceID") String instanceId);


    @UpnpAction(name = "GetCurrentTransportActions", out = @UpnpOutputArgument(name = "Actions", stateVariable = "CurrentTransportActions"))
    String getCurrentTransportActions(@UpnpInputArgument(name = "InstanceID") String instanceId);

}
