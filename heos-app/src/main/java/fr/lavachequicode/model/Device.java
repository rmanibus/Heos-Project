package fr.lavachequicode.model;

import lombok.Data;
import lombok.experimental.SuperBuilder;
import org.fourthline.cling.model.types.UDN;

@Data
@SuperBuilder
public class Device extends Member {

  public UDN getUdn() {
    return UDN.valueOf(id);
  }

  String groupStatus;
  String zoneStatus;
  String audioChannel;
}
