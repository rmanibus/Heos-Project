package fr.lavachequicode.services;

import lombok.extern.slf4j.Slf4j;
import org.fourthline.cling.registry.event.*;
import org.fourthline.cling.registry.event.Phase.Alive;
import org.fourthline.cling.registry.event.Phase.Byebye;
import org.fourthline.cling.registry.event.Phase.Complete;
import org.fourthline.cling.registry.event.Phase.Updated;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;

@ApplicationScoped
@Slf4j
public class HeosRegistryListener {

    void remoteDeviceDiscoveryStarted(@Observes @Alive RemoteDeviceDiscovery event) {
        log.info("Discovery started: {} {}", event.getDevice().getType().getDisplayString(), event.getDevice().getDetails().getFriendlyName());
    }

    void remoteDeviceDiscoveryFailed(@Observes FailedRemoteDeviceDiscovery event) {
        log.info("Discovery failed: {} {}", event.getDevice().getType().getDisplayString(), event.getDevice().getDetails().getFriendlyName());
    }

    void remoteDeviceAdded(@Observes @Complete RemoteDeviceDiscovery event) {
        log.info("Remote device added: {} {}", event.getDevice().getType().getDisplayString(), event.getDevice().getDetails().getFriendlyName());

    }

    void remoteDeviceUpdated(@Observes @Updated RemoteDeviceDiscovery event) {
        log.info("Remote device updated: {} {}", event.getDevice().getType().getDisplayString(), event.getDevice().getDetails().getFriendlyName());

    }

    public void remoteDeviceRemoved(@Observes @Byebye RemoteDeviceDiscovery event) {
        log.info("Remote device removed: {} {}", event.getDevice().getType().getDisplayString(), event.getDevice().getDetails().getFriendlyName());

    }

    public void localDeviceAdded(@Observes @Alive LocalDeviceDiscovery event) {
        log.info("Local device added: {}", event.getDevice().getDisplayString());
    }

    public void localDeviceRemoved(@Observes @Byebye LocalDeviceDiscovery event) {
        log.info("Local device removed: {}", event.getDevice().getDisplayString());
    }

    public void beforeShutdown(@Observes @Before RegistryShutdown event) {
        log.info("Shutting down registry");

    }

    public void afterShutdown(@Observes @After RegistryShutdown event) {

    }
}
