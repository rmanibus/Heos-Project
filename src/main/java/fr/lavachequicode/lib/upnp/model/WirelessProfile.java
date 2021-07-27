package fr.lavachequicode.lib.upnp.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlText;
import lombok.Data;

@Data
@JacksonXmlRootElement(localName = "wirelessProfile")
public class WirelessProfile {

    @JacksonXmlProperty(isAttribute = true, localName = "SSID")
    String ssid;
    WirelessSecurity wirelessSecurity;

    @Data
    public static class WirelessSecurity {
        @JacksonXmlProperty(isAttribute = true)
        boolean enabled;
        @JsonProperty("Mode")
        Mode mode;
    }

    @Data
    public static class Mode {
        @JacksonXmlProperty(isAttribute = true)
        String passPhrase;
        @JacksonXmlText
        String value;
    }
}
