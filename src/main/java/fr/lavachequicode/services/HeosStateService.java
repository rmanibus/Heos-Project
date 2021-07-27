package fr.lavachequicode.services;

import fr.lavachequicode.events.StateUpdated;
import fr.lavachequicode.lib.upnp.model.ACTCurrentState;
import fr.lavachequicode.lib.upnp.model.GroupCurrentState;
import fr.lavachequicode.lib.upnp.services.ACT;
import fr.lavachequicode.lib.upnp.services.GroupControl;
import io.quarkus.runtime.ShutdownEvent;
import org.fourthline.cling.model.meta.Service;
import org.fourthline.cling.model.types.UDN;
import org.fourthline.cling.registry.event.Phase;
import org.fourthline.cling.registry.event.RemoteDeviceDiscovery;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Event;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;

@ApplicationScoped
public class HeosStateService {

    @Inject
    HeosUpnpFactoy heosUpnpFactory;
    @Inject
    Event<StateUpdated> dataUpdatedEvent;


    final Map<String, GroupCurrentState> devicesGroupState = new ConcurrentHashMap<>();
    final Map<String, ACTCurrentState> devicesActState = new ConcurrentHashMap<>();

    public GroupCurrentState getDeviceGroupState(UDN udn) {
        return devicesGroupState.get(udn.getIdentifierString());
    }

    public Collection<String> getGroupStateDevicesIds() {
        return devicesGroupState.keySet();
    }


    protected void remoteDeviceAdded(@Observes @Phase.Alive RemoteDeviceDiscovery event) {

    }

    protected void remoteDeviceUpdated(@Observes @Phase.Updated RemoteDeviceDiscovery event) {
        final AtomicBoolean updated = new AtomicBoolean(false);
        Optional.ofNullable(event.getDevice().findService(GroupControl.serviceId)).ifPresent((service) -> {
            devicesGroupState.put(event.getDevice().getIdentity().getUdn().getIdentifierString(), getGroupControl(service).getCurrentState());
            updated.set(true);
        });

        Optional.ofNullable(event.getDevice().findService(ACT.serviceId)).ifPresent((service) -> {
            devicesActState.put(event.getDevice().getIdentity().getUdn().getIdentifierString(), getAct(service).getCurrentState());
            updated.set(true);
        });

        if(updated.get()){
            dataUpdatedEvent.fire(new StateUpdated());
        }
    }

    protected void remoteDeviceRemoved(@Observes @Phase.Byebye RemoteDeviceDiscovery event) {
        devicesGroupState.remove(event.getDevice().getIdentity().getUdn().getIdentifierString());
        devicesActState.remove(event.getDevice().getIdentity().getUdn().getIdentifierString());
        dataUpdatedEvent.fire(new StateUpdated());
    }

    protected void shutdown(@Observes ShutdownEvent e){
        devicesGroupState.clear();
    }

    protected GroupControl getGroupControl(Service service) {
        return heosUpnpFactory.createProxy(service, GroupControl.class);
    }

    protected ACT getAct(Service service) {
        return heosUpnpFactory.createProxy(service, ACT.class);
    }
}
