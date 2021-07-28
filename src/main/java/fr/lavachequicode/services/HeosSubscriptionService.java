package fr.lavachequicode.services;

import fr.lavachequicode.lib.upnp.model.GroupCurrentState;
import org.fourthline.cling.controlpoint.ControlPoint;
import org.fourthline.cling.model.meta.Service;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class HeosSubscriptionService {

    @Inject
    ControlPoint controlPoint;


    public void subscribe(Service service, Object currentState) {
        HeosSubscriptionCallback callback = new HeosSubscriptionCallback(service, currentState);
        controlPoint.execute(callback);
    }
}
