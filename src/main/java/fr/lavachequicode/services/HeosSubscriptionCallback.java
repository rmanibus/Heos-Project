package fr.lavachequicode.services;

import lombok.extern.slf4j.Slf4j;
import org.fourthline.cling.controlpoint.SubscriptionCallback;
import org.fourthline.cling.model.gena.CancelReason;
import org.fourthline.cling.model.gena.GENASubscription;
import org.fourthline.cling.model.message.UpnpResponse;
import org.fourthline.cling.model.meta.Service;

@Slf4j
public class HeosSubscriptionCallback extends SubscriptionCallback {

    protected HeosSubscriptionCallback(Service service) {
        super(service, 180);
    }

    @Override
    protected void failed(GENASubscription subscription, UpnpResponse responseStatus, Exception exception, String defaultMsg) {
        log.error("error: {}", defaultMsg);
    }

    @Override
    protected void established(GENASubscription subscription) {
        log.info("established: {}", subscription.getSubscriptionId());
        log.info("values: {}", subscription.getCurrentValues());
    }

    @Override
    protected void ended(GENASubscription subscription, CancelReason reason, UpnpResponse responseStatus) {
        log.info("ended: {}", subscription.getSubscriptionId());
    }

    @Override
    protected void eventReceived(GENASubscription subscription) {
        log.info("received: {} {}", subscription.getSubscriptionId(), subscription.getCurrentSequence().getValue());
        log.info("values: {}", subscription.getCurrentValues());

    }

    @Override
    protected void eventsMissed(GENASubscription subscription, int numberOfMissedEvents) {
        log.info("subscription {}: missed {} events", subscription.getSubscriptionId(), numberOfMissedEvents);
    }
}
