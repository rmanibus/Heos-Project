package fr.lavachequicode.lib.upnp.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Data;

import java.util.List;

@Data
public class ListNetworkConfigurations {

    @JacksonXmlElementWrapper(useWrapping = false)
    List<NetworkConfiguration> networkConfiguration;

    @Data
    public static class NetworkConfiguration {
        @JacksonXmlProperty(isAttribute = true)
        Integer id;
        @JacksonXmlProperty(isAttribute = true)
        Integer dhcpOn;
        @JacksonXmlProperty(isAttribute = true)
        boolean enabled;
        @JsonProperty("Name")
        String name;
        @JsonProperty("Type")
        String type;
        @JsonProperty("IP")
        String ip;
        @JsonProperty("Netmask")
        String netmask;
        @JsonProperty("Gateway")
        String gateway;
        @JsonProperty("DNS1")
        String DNS1;
        @JsonProperty("DNS2")
        String DNS2;
        @JsonProperty("DNS3")
        String DNS3;
        @JsonProperty("gwMac")
        String gwMac;
    }
}
