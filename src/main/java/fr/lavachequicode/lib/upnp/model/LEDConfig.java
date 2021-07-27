package fr.lavachequicode.lib.upnp.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import lombok.Data;

import java.util.List;
@Data
public class LEDConfig {

    @JacksonXmlElementWrapper(useWrapping = false)
    List<Led> led;

    @JsonIgnoreProperties(ignoreUnknown = true)
    @Data
    public static class Led {
        String name;
        Integer brightness;
        Integer fadeOutDelay;
        Integer enable;
        @JsonProperty("default")
        Default aDefault;
    }

    @Data
    public static class Default {
        Integer brightness;
        Integer fadeOutDelay;
        Integer enable;
    }
}
