package fr.lavachequicode.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class Zone {
  String id;
  String friendlyName;
  Member leader;
  List<? extends Member> members;
}
