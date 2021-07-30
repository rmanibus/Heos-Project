package fr.lavachequicode.heos.sdk.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES;

@Data
public class AVTCurrentState {

    static XmlMapper xmlMapper = new XmlMapper();

    {
        xmlMapper.configure(FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    @JsonProperty("InstanceID")
    InstanceID state;

    @Data
    @Slf4j
    public static class InstanceID {
        @JacksonXmlProperty(
                isAttribute = true, localName = "val")
        String instanceId;
        @JsonProperty("AVTransportURI")
        void unpackAVTransportURI(Map<String, String> data) {
            avTransportURI = data.get("val");
        }
        String avTransportURI;
        @JsonProperty("CurrentMediaDuration")
        void unpackCurrentMediaDuration(Map<String, String> data) {
            currentMediaDuration = data.get("val");
        }
        String currentMediaDuration;
        @JsonProperty("CurrentPlayMode")
        void unpackCurrentPlayMode(Map<String, String> data) {
            currentPlayMode = data.get("val");
        }
        String currentPlayMode;
        @JsonProperty("CurrentTrack")
        void unpackCurrentTrack(Map<String, String> data) {
            currentTrack = data.get("val");
        }
        String currentTrack;
        @JsonProperty("CurrentTrackDuration")
        void unpackCurrentTrackDuration(Map<String, String> data) {
            currentTrackDuration = data.get("val");
        }
        String currentTrackDuration;
        @JsonProperty("CurrentTrackURI")
        void unpackCurrentTrackURI(Map<String, String> data) {
            currentTrackURI = data.get("val");
        }
        String currentTrackURI;
        @JsonProperty("NextAVTransportURI")
        void unpackNextAVTransportURI(Map<String, String> data) {
            nextAVTransportURI = data.get("val");
        }
        String nextAVTransportURI;
        @JsonProperty("NumberOfTracks")
        void unpackNumberOfTracks(Map<String, String> data) {
            numberOfTracks = data.get("val");
        }
        String numberOfTracks;
        @JsonProperty("PlaybackStorageMedium")
        void unpackPlaybackStorageMedium(Map<String, String> data) {
            playbackStorageMedium = data.get("val");
        }
        String playbackStorageMedium;
        @JsonProperty("PossiblePlaybackStorageMedia")
        void unpackPossiblePlaybackStorageMedia(Map<String, String> data) {
            possiblePlaybackStorageMedia = data.get("val");
        }
        String possiblePlaybackStorageMedia;
        @JsonProperty("PossibleRecordStorageMedia")
        void unpackPossibleRecordStorageMedia(Map<String, String> data) {
            possibleRecordStorageMedia = data.get("val");
        }
        String possibleRecordStorageMedia;
        @JsonProperty("TransportPlaySpeed")
        void unpackTransportPlaySpeed(Map<String, String> data) {
            transportPlaySpeed = data.get("val");
        }
        String transportPlaySpeed;
        @JsonProperty("TransportState")
        void unpackTransportState(Map<String, String> data) {
            transportState = data.get("val");
        }
        String transportState;
        @JsonProperty("TransportStatus")
        void unpackTransportStatus(Map<String, String> data) {
            transportStatus = data.get("val");
        }
        String transportStatus;
        @JsonProperty("X_Shuffle")
        void unpackX_Shuffle(Map<String, String> data) {
            x_Shuffle = data.get("val");
        }
        String x_Shuffle;
        @JsonProperty("CurrentTransportActions")
        void unpackCurrentTransportActions(Map<String, String> data) {
            currentTransportActions = data.get("val");
        }
        String currentTransportActions;

        @JsonProperty("AVTransportURIMetaData")
        void unpackAvTransportURIMetaData(Map<String, String> data) throws JsonProcessingException {
            avTransportURIMetaData = readNested(data.get("val"), MetaData.class);
        }

        MetaData avTransportURIMetaData;

        @JsonProperty("CurrentTrackMetaData")
        void unpackCurrentTrackMetaData(Map<String, String> data) throws JsonProcessingException {
            currentTrackMetaData = readNested(data.get("val"), MetaData.class);
        }

        MetaData currentTrackMetaData;

        @JsonProperty("NextAVTransportURIMetaData")
        void unpackNextAVTransportURIMetaData(Map<String, String> data) throws JsonProcessingException {
            nextAVTransportURIMetaData = readNested(data.get("val"), MetaData.class);
        }

        MetaData nextAVTransportURIMetaData;
    }

    static <T> T readNested(String value, Class<T> tClass) throws JsonProcessingException {

        if (value == null || value.length() == 0) {
            return null;
        }
        return (T) xmlMapper.readValue(value, tClass);
    }
}
