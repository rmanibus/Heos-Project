package fr.lavachequicode.services;

import fr.lavachequicode.lib.upnp.services.ZoneControl;
import org.fourthline.cling.controlpoint.ControlPoint;
import org.fourthline.cling.model.meta.Device;
import org.fourthline.cling.registry.event.Phase;
import org.fourthline.cling.registry.event.RemoteDeviceDiscovery;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@ApplicationScoped
public class HeosSubscriptionService {

    @Inject
    ControlPoint controlPoint;

    final Map<String, HeosSubscriptionCallback> callbacks = new ConcurrentHashMap<>();

    protected void remoteDeviceAdded(@Observes @Phase.Alive RemoteDeviceDiscovery event) {
        updateDevice(event.getDevice());
    }

    protected void remoteDeviceUpdated(@Observes @Phase.Updated RemoteDeviceDiscovery event) {
        updateDevice(event.getDevice());
    }

    protected void updateDevice(Device device) {
        callbacks.computeIfAbsent(device.getIdentity().getUdn().getIdentifierString(), udn -> {
            HeosSubscriptionCallback callback = new HeosSubscriptionCallback(device.findService(ZoneControl.serviceId));
            controlPoint.execute(callback);
            return callback;
        });
    }
}
