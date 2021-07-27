package fr.lavachequicode.lib.upnp.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;

import java.util.List;

public class LEDConfig {

    @JacksonXmlElementWrapper(useWrapping = false)
    List<Led> led;

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Led {
        String name;
        Integer brightness;
        Integer fadeOutDelay;
        boolean enable;
        @JsonProperty("default")
        Default aDefault;
    }

    public static class Default {
        Integer brightness;
        Integer fadeOutDelay;
        boolean enable;

    }
}
