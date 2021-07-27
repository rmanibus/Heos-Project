package fr.lavachequicode.web.dto;

import lombok.Data;
import org.fourthline.cling.model.meta.Service;
import org.fourthline.cling.model.types.ServiceId;
import org.fourthline.cling.model.types.ServiceType;

import java.util.Arrays;

@Data
public class ServiceDto {

    final private ServiceType serviceType;
    final private ServiceId serviceId;

    final private ActionDto[] actions;
    final private StateVariableDto[] stateVariables;

    public ServiceDto(Service service){
        this.serviceType = service.getServiceType();
        this.serviceId = service.getServiceId();
        this.actions = Arrays.stream(service.getActions()).map(ActionDto::new).toArray(ActionDto[]::new);
        this.stateVariables = Arrays.stream(service.getStateVariables()).map(StateVariableDto::new).toArray(StateVariableDto[]::new);
    }
}
