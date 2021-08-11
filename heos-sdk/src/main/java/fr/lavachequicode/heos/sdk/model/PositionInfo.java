package fr.lavachequicode.heos.sdk.model;

import lombok.Data;

@Data
public class PositionInfo {
    Integer track;
    String trackDuration;
    MetaData trackMetaData;
    String trackURI;
    String relTime;
    String absTime;
    String relativeCounterPosition;
    String absCount;
}
