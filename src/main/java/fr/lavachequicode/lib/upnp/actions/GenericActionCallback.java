package fr.lavachequicode.lib.upnp.actions;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.fourthline.cling.controlpoint.ActionCallback;
import org.fourthline.cling.model.action.ActionInvocation;
import org.fourthline.cling.model.message.UpnpResponse;



@Slf4j
public class GenericActionCallback extends ActionCallback {

    @Getter
    private Status status = Status.IN_PROGRESS;
    @Getter
    private ActionInvocation invocation;

    protected GenericActionCallback(ActionInvocation actionInvocation) {
        super(actionInvocation);
    }

    @Override
    public void success(ActionInvocation invocation) {
        this.invocation = invocation;
        this.status = Status.SUCCESS;
        log.info("success: {}", invocation.getAction().getName());
    }

    @Override
    public void failure(ActionInvocation invocation, UpnpResponse operation, String defaultMsg) {
        this.invocation = invocation;
        this.status = Status.FAILURE;
        log.error("error: {} {}", invocation.getAction().getName(), defaultMsg);
    }

    public enum Status {
        IN_PROGRESS,
        SUCCESS,
        FAILURE
    }
}
