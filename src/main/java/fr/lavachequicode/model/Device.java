package fr.lavachequicode.model;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class Device extends Member {
    String groupStatus;
    String zoneStatus;
}
