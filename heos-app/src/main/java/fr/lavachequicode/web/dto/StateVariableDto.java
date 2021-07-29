package fr.lavachequicode.web.dto;

import lombok.Data;
import org.fourthline.cling.model.meta.StateVariable;
import org.fourthline.cling.model.meta.StateVariableEventDetails;
import org.fourthline.cling.model.meta.StateVariableTypeDetails;

@Data
public class StateVariableDto {

    final private String name;
    final private StateVariableTypeDetails type;
    final private StateVariableEventDetails eventDetails;

    public StateVariableDto(StateVariable stateVariable) {
        this.name = stateVariable.getName();
        this.type = stateVariable.getTypeDetails();
        this.eventDetails = stateVariable.getEventDetails();
    }
}
