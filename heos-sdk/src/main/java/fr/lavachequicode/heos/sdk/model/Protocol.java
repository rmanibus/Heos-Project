package fr.lavachequicode.heos.sdk.model;

import java.util.logging.Logger;

public enum Protocol {
    ALL(ProtocolInfo.WILDCARD),
    HTTP_GET("http-get"),
    RTSP_RTP_UDP("rtsp-rtp-udp"),
    INTERNAL("internal"),
    IEC61883("iec61883"),
    XBMC_GET("xbmc-get"),
    OTHER("other");

    private static final Logger LOG = Logger.getLogger(Protocol.class.getName());

    private String protocolString;

    Protocol(String protocolString) {
        this.protocolString = protocolString;
    }

    @Override
    public String toString() {
        return protocolString;
    }

    public static Protocol value(String s) {
        for (Protocol protocol : values()) {
            if (protocol.toString().equals(s)) {
                return protocol;
            }
        }
        LOG.info("Unsupported OTHER protocol string: " + s);
        return OTHER;
    }
}
