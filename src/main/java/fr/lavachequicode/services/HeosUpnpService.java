package fr.lavachequicode.services;

import io.quarkus.runtime.StartupEvent;
import lombok.extern.slf4j.Slf4j;
import org.fourthline.cling.UpnpService;
import org.fourthline.cling.controlpoint.ControlPoint;
import org.fourthline.cling.model.message.header.UDADeviceTypeHeader;
import org.fourthline.cling.model.types.DeviceType;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Event;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

@ApplicationScoped
@Slf4j
public class HeosUpnpService {

    @Inject
    ControlPoint controlPoint;
    @Inject
    Event<UpnpService.Start> startEvent;

    void setup(@Observes StartupEvent e) {
        startEvent.fire(new UpnpService.Start());
        scan();
    }

    public void scan() {
        log.info("searching for devices ...");
        DeviceType type = new DeviceType("schemas-denon-com", "ACT-Denon");
        controlPoint.search(new UDADeviceTypeHeader(type));
    }
}
