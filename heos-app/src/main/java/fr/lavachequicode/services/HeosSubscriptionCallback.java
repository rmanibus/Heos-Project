package fr.lavachequicode.services;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.fourthline.cling.controlpoint.SubscriptionCallback;
import org.fourthline.cling.model.gena.CancelReason;
import org.fourthline.cling.model.gena.GENASubscription;
import org.fourthline.cling.model.message.UpnpResponse;
import org.fourthline.cling.model.meta.Service;

@Slf4j
public class HeosSubscriptionCallback extends SubscriptionCallback {

    final Object currentState;

    protected HeosSubscriptionCallback(Service service, Object currentState) {
        super(service, 180);
        this.currentState = currentState;
    }

    @Override
    protected void failed(GENASubscription subscription, UpnpResponse responseStatus, Exception exception, String defaultMsg) {
        log.error("error: {}", defaultMsg);
    }

    @Override
    protected void established(GENASubscription subscription) {
        log.info("established: {}", subscription.getSubscriptionId());
    }

    @Override
    protected void ended(GENASubscription subscription, CancelReason reason, UpnpResponse responseStatus) {
        log.info("ended: {}", subscription.getSubscriptionId());

    }

    @SneakyThrows
    @Override
    protected void eventReceived(GENASubscription subscription) {
        log.info("received: {} {}", subscription.getSubscriptionId(), subscription.getCurrentSequence().getValue());
        XmlMapper xmlMapper = new XmlMapper();
        xmlMapper.readerForUpdating(currentState).readValue(subscription.getCurrentValues().get("LastChange").toString());
    }

    @Override
    protected void eventsMissed(GENASubscription subscription, int numberOfMissedEvents) {
        log.info("subscription {}: missed {} events", subscription.getSubscriptionId(), numberOfMissedEvents);
    }
}
