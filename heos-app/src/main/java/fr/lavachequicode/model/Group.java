package fr.lavachequicode.model;

import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@SuperBuilder
public class Group extends Member {
    public static final String NO_GROUP_ID = "00000000000000000000000000000000";
    Integer volume;
    Device leader;
    List<Device> members;
}
