package fr.lavachequicode.services;

import fr.lavachequicode.events.StateUpdated;
import fr.lavachequicode.lib.upnp.model.ACTCurrentState;
import fr.lavachequicode.lib.upnp.model.GroupCurrentState;
import fr.lavachequicode.lib.upnp.model.ZoneCurrentState;
import fr.lavachequicode.lib.upnp.services.ACT;
import fr.lavachequicode.lib.upnp.services.GroupControl;
import fr.lavachequicode.lib.upnp.services.ZoneControl;
import fr.lavachequicode.model.Zone;
import io.quarkus.runtime.ShutdownEvent;
import lombok.extern.slf4j.Slf4j;
import org.fourthline.cling.model.meta.Device;
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
@Slf4j
public class HeosStateService {

    @Inject
    HeosUpnpFactoy heosUpnpFactory;
    @Inject
    Event<StateUpdated> dataUpdatedEvent;


    final Map<String, GroupCurrentState> devicesGroupState = new ConcurrentHashMap<>();
    final Map<String, ZoneCurrentState> devicesZoneState = new ConcurrentHashMap<>();
    final Map<String, ACTCurrentState> devicesActState = new ConcurrentHashMap<>();

    public GroupCurrentState getDeviceGroupState(UDN udn) {
        return devicesGroupState.get(udn.getIdentifierString());
    }

    public boolean containsGroupStateForDevice(UDN udn) {
        return devicesGroupState.containsKey(udn.getIdentifierString());
    }

    public ZoneCurrentState getDeviceZoneState(UDN udn) {
        return devicesZoneState.get(udn.getIdentifierString());
    }

    public boolean containsZoneStateForDevice(UDN udn) {
        return devicesZoneState.containsKey(udn.getIdentifierString());
    }

    protected void remoteDeviceAdded(@Observes @Phase.Alive RemoteDeviceDiscovery event) {
        updateDevice(event.getDevice());
    }

    protected void remoteDeviceUpdated(@Observes @Phase.Updated RemoteDeviceDiscovery event) {
        updateDevice(event.getDevice());
    }

    /*
    protected void remoteDeviceRemoved(@Observes @Phase.Byebye RemoteDeviceDiscovery event) {
        devicesGroupState.remove(event.getDevice().getIdentity().getUdn().getIdentifierString());
        devicesActState.remove(event.getDevice().getIdentity().getUdn().getIdentifierString());
        dataUpdatedEvent.fire(new StateUpdated());
    }
    */
    protected void shutdown(@Observes ShutdownEvent e) {
        devicesGroupState.clear();
    }

    protected void updateDevice(Device device) {
        final AtomicBoolean updated = new AtomicBoolean(false);

        if (device.isFullyHydrated()) {
            Optional.ofNullable(device.findService(GroupControl.serviceId)).ifPresent((service) -> {
                devicesGroupState.put(device.getIdentity().getUdn().getIdentifierString(), getGroupControl(service).getCurrentState());
                updated.set(true);
            });
            Optional.ofNullable(device.findService(ZoneControl.serviceId)).ifPresent((service) -> {
                devicesZoneState.put(device.getIdentity().getUdn().getIdentifierString(), getZoneControl(service).getCurrentState());
                updated.set(true);
            });
            Optional.ofNullable(device.findService(ACT.serviceId)).ifPresent((service) -> {
                devicesActState.put(device.getIdentity().getUdn().getIdentifierString(), getAct(service).getCurrentState());
                updated.set(true);
            });
        }

        if (updated.get()) {
            dataUpdatedEvent.fire(new StateUpdated());
        }
    }

    protected GroupControl getGroupControl(Service service) {
        return heosUpnpFactory.createProxy(service, GroupControl.class);
    }

    protected ZoneControl getZoneControl(Service service) {
        return heosUpnpFactory.createProxy(service, ZoneControl.class);
    }
    protected ACT getAct(Service service) {
        return heosUpnpFactory.createProxy(service, ACT.class);
    }
}
