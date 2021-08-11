package fr.lavachequicode.heos.sdk.model;

import lombok.Data;

@Data
public class PositionInfo {
    String track;
    String trackDuration;
    MetaData trackMetaData;
    String trackURI;
    String relTime;
    String absTime;
    String relativeCounterPosition;
    String absCount;
}
