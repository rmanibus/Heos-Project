package fr.lavachequicode.web.dto;

import lombok.Data;
import org.fourthline.cling.model.meta.ActionArgument;
@Data
public class ActionArgumentDto {
    final private String name;
    final private String[] aliases;
    final private String relatedStateVariableName;
    final private ActionArgument.Direction direction;
    final private boolean returnValue;     // TODO: What is this stuff good for anyway?

    public ActionArgumentDto(ActionArgument actionArgument){
        this.name = actionArgument.getName();
        this.aliases = actionArgument.getAliases();
        this.relatedStateVariableName = actionArgument.getRelatedStateVariableName();
        this.direction = actionArgument.getDirection();
        this.returnValue = actionArgument.isReturnValue();
    }
}
