package fr.lavachequicode.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Zone {
    String id;
    String friendlyName;
    GroupMember leader;
    List<GroupMember> members;
}
