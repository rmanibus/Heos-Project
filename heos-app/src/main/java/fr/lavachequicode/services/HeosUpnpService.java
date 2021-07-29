package fr.lavachequicode.services;

import fr.lavachequicode.heos.sdk.devices.AiosDevice;
import io.quarkus.runtime.StartupEvent;
import io.quarkus.scheduler.Scheduled;
import lombok.extern.slf4j.Slf4j;
import org.fourthline.cling.UpnpService;
import org.fourthline.cling.controlpoint.ControlPoint;
import org.fourthline.cling.model.message.header.UDADeviceTypeHeader;

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
    }

    @Scheduled(every="60s")
    public void scan() {
        log.info("searching for devices ...");
        controlPoint.search(new UDADeviceTypeHeader(AiosDevice.type));
    }
}
