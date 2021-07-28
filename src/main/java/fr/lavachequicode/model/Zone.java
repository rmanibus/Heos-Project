package fr.lavachequicode.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Zone {
    String id;
    String friendlyName;
    Member leader;
    List<? extends Member> members;
}
