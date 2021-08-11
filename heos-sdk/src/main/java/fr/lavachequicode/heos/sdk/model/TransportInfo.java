package fr.lavachequicode.heos.sdk.model;

import lombok.Data;

@Data
public class TransportInfo {

    String currentTransportState;
    String currentTransportStatus;
    Integer currentSpeed;
}
