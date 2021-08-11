package fr.lavachequicode.web.dto;

import fr.lavachequicode.heos.sdk.model.MetaData;
import fr.lavachequicode.heos.sdk.model.PositionInfo;
import fr.lavachequicode.heos.sdk.model.TransportInfo;
import lombok.Data;

@Data
public class PlaybackStatusDto {
  public PlaybackStatusDto(PositionInfo positionInfo, TransportInfo transportInfo) {
    transportState = transportInfo.getCurrentTransportState();
    transportStatus = transportInfo.getCurrentTransportStatus();
    speed = transportInfo.getCurrentSpeed();
    metadata = new MetadataDto(positionInfo.getTrackMetaData());
    relTime = positionInfo.getRelTime();
    absTime = positionInfo.getAbsTime();
    relativeCounterPosition = positionInfo.getRelativeCounterPosition();
    absCount = positionInfo.getAbsCount();
    track = positionInfo.getTrack();
    trackDuration = positionInfo.getTrackDuration();
  }

  MetadataDto metadata;
  String transportState;
  String transportStatus;
  Integer speed;
  String relTime;
  String absTime;
  String relativeCounterPosition;
  String absCount;
  Integer track;
  String trackDuration;

  @Data
  public static class MetadataDto {
    public MetadataDto(MetaData metaData) {
      dcTitle = metaData.getItem().getDcTitle();
      artist = metaData.getItem().getArtist();
      album = metaData.getItem().getAlbum();
      albumArtURI = metaData.getItem().getAlbumArtURI();
    }

    String dcTitle;
    String artist;
    String album;
    String albumArtURI;
  }
}

