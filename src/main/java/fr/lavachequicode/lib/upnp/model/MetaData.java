package fr.lavachequicode.lib.upnp.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlText;
import lombok.Data;

@JacksonXmlRootElement(namespace = "urn:schemas-upnp-org:metadata-1-0/DIDL-Lite/", localName = "DIDL-Lite")
@Data
public class MetaData {

    Item item;

    @Data
    public static class Item {
        @JacksonXmlProperty(
                isAttribute = true, localName = "id")
        String id;
        @JacksonXmlProperty(
                isAttribute = true, localName = "parentId")
        Integer parentId;
        @JacksonXmlProperty(
                isAttribute = true, localName = "restricted")
        Integer restricted;

        @JacksonXmlProperty(namespace = "http://purl.org/dc/elements/1.1/", localName = "title")
        String dcTitle;
        @JsonProperty("Res")
        Res res;
        @JacksonXmlProperty(namespace = "urn:schemas-upnp-org:metadata-1-0/upnp/", localName = "class")
        String upnpClass;
        @JacksonXmlProperty(namespace = "http://purl.org/dc/elements/1.1/", localName = "creator")
        String dcCreator;
        @JacksonXmlProperty(namespace = "urn:schemas-upnp-org:metadata-1-0/upnp/", localName = "genre")
        String genre;
        @JacksonXmlProperty(namespace = "urn:schemas-upnp-org:metadata-1-0/upnp/", localName = "artist")
        String artist;
        @JacksonXmlProperty(namespace = "urn:schemas-upnp-org:metadata-1-0/upnp/", localName = "album")
        String album;
        @JacksonXmlProperty(namespace = "urn:schemas-upnp-org:metadata-1-0/upnp/", localName = "albumArtURI")
        String albumArtURI;
    }

    @Data
    public static class Res {

        @JacksonXmlProperty(
                isAttribute = true, localName = "protocolInfo")
        String protocolInfo;
        @JacksonXmlProperty(
                isAttribute = true, localName = "size")
        Integer size;
        @JacksonXmlProperty(
                isAttribute = true, localName = "duration")
        String duration;
        @JacksonXmlText
        String value;
    }
}
