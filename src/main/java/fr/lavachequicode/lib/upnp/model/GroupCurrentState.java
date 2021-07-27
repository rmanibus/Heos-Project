package fr.lavachequicode.lib.upnp.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;

@Data
@JacksonXmlRootElement(namespace = "urn:schemas-upnp-org:metadata-1-0/GCS/", localName = "Event")
public class GroupCurrentState {

    @JsonProperty("ExtAPSignalStrength")
    Content extAPSignalStrength;
    @JsonProperty("DeviceFriendlyName")

    Content deviceFriendlyName;
    @JsonProperty("GroupFriendlyName")

    Content groupFriendlyName;
    @JsonProperty("GroupStatus")
    Content groupStatus;
    @JsonProperty("GroupVolume")
    Content groupVolume;
    @JsonProperty("GroupBalance")
    Content groupBalance;
    @JsonProperty("GroupMute")
    Content groupMute;
    @JsonProperty("GroupTreble")
    Content groupTreble;
    @JsonProperty("GroupBass")
    Content groupBass;
    @JsonProperty("AudioChannel")
    Content audioChannel;
    @JsonProperty("CommandID")
    Content commandID;
    @JsonProperty("GroupUUID")
    Content groupUUID;
    @JsonProperty("GroupUpdating")
    Content groupUpdating;
    @JsonProperty("ConfigDeviceUUID")
    Content configDeviceUUID;
    @JsonProperty("MediaServerUUID")
    Content mediaServerUUID;

    @Data
    public static class Content {
        @JacksonXmlProperty(
                isAttribute = true, localName = "val")
        String value;
    }
}
