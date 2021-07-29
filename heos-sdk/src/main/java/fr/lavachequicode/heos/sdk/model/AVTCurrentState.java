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

    @Data
    public static class Content {
        @JacksonXmlProperty(
                isAttribute = true, localName = "val")
        String value;
    }

    static <T> T readNested(String value, Class<T> tClass) throws JsonProcessingException {

        if (value == null || value.length() == 0) {
            return null;
        }
        return (T) xmlMapper.readValue(value, tClass);
    }
}
