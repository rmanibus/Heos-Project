package fr.lavachequicode.services;

import fr.lavachequicode.lib.upnp.actions.ActionInvocationHandler;
import org.fourthline.cling.UpnpService;
import org.fourthline.cling.model.meta.Service;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class HeosUpnpFactoy {

    @Inject
    UpnpService upnpService;
    public <T> T createProxy(Service service,  Class<T> tClass){
        return ActionInvocationHandler.createProxy(upnpService, service, tClass);
    }
}
