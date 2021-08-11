package fr.lavachequicode.heos.sdk.model;

import lombok.Data;

@Data
public class Browse {
    DIDLLite result;
    String numberReturned;
    String totalMatches;
    String updateID;
}
