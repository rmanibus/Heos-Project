package fr.lavachequicode.lib.upnp.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Data;

@Data
public class AVTCurrentState {
    @JsonProperty("InstanceID")
    InstanceID state;

    @Data
    public static class InstanceID {
        @JacksonXmlProperty(
                isAttribute = true, localName = "val")
        String instanceId;
        @JsonProperty("AVTransportURI")
        Content avTransportURI;
        @JsonProperty("CurrentMediaDuration")
        Content currentMediaDuration;
        @JsonProperty("CurrentPlayMode")
        Content currentPlayMode;
        @JsonProperty("CurrentTrack")
        Content currentTrack;
        @JsonProperty("CurrentTrackDuration")
        Content currentTrackDuration;
        @JsonProperty("CurrentTrackURI")
        Content currentTrackURI;
        @JsonProperty("NextAVTransportURI")
        Content nextAVTransportURI;
        @JsonProperty("NumberOfTracks")
        Content numberOfTracks;
        @JsonProperty("PlaybackStorageMedium")
        Content playbackStorageMedium;
        @JsonProperty("PossiblePlaybackStorageMedia")
        Content possiblePlaybackStorageMedia;
        @JsonProperty("PossibleRecordStorageMedia")
        Content possibleRecordStorageMedia;
        @JsonProperty("TransportPlaySpeed")
        Content transportPlaySpeed;
        @JsonProperty("TransportState")
        Content transportState;
        @JsonProperty("TransportStatus")
        Content transportStatus;
        @JsonProperty("X_Shuffle")
        Content x_Shuffle;
        @JsonProperty("CurrentTransportActions")
        Content currentTransportActions;
        @JsonProperty("AVTransportURIMetaData")
        Content avTransportURIMetaData;
        @JsonProperty("CurrentTrackMetaData")
        Content currentTrackMetaData;
        @JsonProperty("NextAVTransportURIMetaData")
        Content nextAVTransportURIMetaData;
    }

    @Data
    public static class Content {
        @JacksonXmlProperty(
                isAttribute = true, localName = "val")
        String value;
    }
}
