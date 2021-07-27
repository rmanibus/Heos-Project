package fr.lavachequicode.lib.upnp.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;

@Data
@JacksonXmlRootElement(namespace = "urn:schemas-upnp-org:metadata-1-0/ZCS/", localName = "Event")
public class ZoneCurrentState {

    @JsonProperty("ZoneConnectedList")
    Content ZoneConnectedList;
    @JsonProperty("ZoneFriendlyName")
    Content ZoneFriendlyName;
    @JsonProperty("ZoneMemberList")
    Content ZoneMemberList;
    @JsonProperty("ZoneMemberStatusList")
    Content ZoneMemberStatusList;
    @JsonProperty("ZoneMute")
    Content ZoneMute;
    @JsonProperty("ZoneStatus")
    Content ZoneStatus;
    @JsonProperty("ZoneVolume")
    Content ZoneVolume;
    @JsonProperty("ZoneMinimise")
    Content ZoneMinimise;
    @JsonProperty("ZoneUUID")
    Content ZoneUUID;

    @Data
    public static class Content {
        @JacksonXmlProperty(isAttribute = true, localName = "val")
        String value;
    }
}
