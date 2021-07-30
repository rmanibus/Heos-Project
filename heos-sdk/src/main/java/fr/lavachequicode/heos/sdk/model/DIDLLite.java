package fr.lavachequicode.heos.sdk.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlText;
import lombok.Data;

import java.util.List;

@JacksonXmlRootElement(namespace = "urn:schemas-upnp-org:metadata-1-0/DIDL-Lite/", localName = "DIDL-Lite")
@Data
public class DIDLLite {

    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "item")
    List<Item> items;

    @Data
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Item {
        @JacksonXmlProperty(isAttribute = true, localName = "id")
        String id;
        @JacksonXmlProperty(isAttribute = true, localName = "restricted")
        String restricted;
        @JacksonXmlProperty(isAttribute = true, localName = "parentID")
        String parentId;
        @JacksonXmlProperty(namespace = "http://purl.org/dc/elements/1.1/", localName = "title")
        String dcTitle;
        @JacksonXmlProperty(namespace = "http://purl.org/dc/elements/1.1/", localName = "creator")
        String dcCreator;
        @JacksonXmlProperty(namespace = "urn:schemas-upnp-org:metadata-1-0/upnp/", localName = "class")
        String upnpClass;
        @JacksonXmlElementWrapper(useWrapping = false)
        List<Desc> desc;
        @JacksonXmlProperty(namespace = "urn:schemas-upnp-org:metadata-1-0/upnp/", localName = "res")
        String res;
        @JacksonXmlProperty(namespace = "urn:schemas-upnp-org:metadata-1-0/upnp/", localName = "originalTrackNumber")
        String upnpOriginalTrackNumber;
        @JacksonXmlProperty(namespace = "urn:schemas-upnp-org:metadata-1-0/upnp/", localName = "genre")
        String genre;
    }

    @Data
    public static class Desc {
        @JacksonXmlProperty(isAttribute = true, localName = "id")
        String id;
        @JacksonXmlProperty(isAttribute = true, localName = "nameSpace")
        String namespace;
        @JacksonXmlText
        String value;
    }
}
