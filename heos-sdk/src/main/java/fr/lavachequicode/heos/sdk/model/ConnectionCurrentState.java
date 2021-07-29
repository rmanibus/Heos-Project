package fr.lavachequicode.heos.sdk.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Data;

@Data
public class ConnectionCurrentState {
    @JsonProperty("RcsID")
    Content RcsID;
    @JsonProperty("AVTransportID")
    Content AVTransportID;
    @JsonProperty("ProtocolInfo")
    Content ProtocolInfo;
    @JsonProperty("PeerConnectionManager")
    Content PeerConnectionManager;
    @JsonProperty("PeerConnectionID")
    Content PeerConnectionID;
    @JsonProperty("Direction")
    Content Direction;
    @JsonProperty("Status")
    Content Status;
    @JsonProperty("SourceProtocolInfo")
    Content SourceProtocolInfo;
    @JsonProperty("SinkProtocolInfo")
    Content SinkProtocolInfo;

    @Data
    public static class Content {
        @JacksonXmlProperty(isAttribute = true, localName = "val")
        String value;
    }
}
