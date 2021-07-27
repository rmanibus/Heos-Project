package fr.lavachequicode.web.dto;

import lombok.Data;
import org.fourthline.cling.model.meta.Action;

import java.util.Arrays;

@Data
public class ActionDto {
    final private String name;
    final private ActionArgumentDto[] inputArguments;
    final private ActionArgumentDto[] outputArguments;
    public ActionDto(Action action){
        this.name = action.getName();
        this.inputArguments = Arrays.stream(action.getInputArguments()).map(ActionArgumentDto::new).toArray(ActionArgumentDto[]::new);
        this.outputArguments = Arrays.stream(action.getOutputArguments()).map(ActionArgumentDto::new).toArray(ActionArgumentDto[]::new);

    }
}
