package fr.lavachequicode.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GroupMember {
    String udn;
    String friendlyName;
    String status;
}
