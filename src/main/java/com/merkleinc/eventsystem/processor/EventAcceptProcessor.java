package com.merkleinc.eventsystem.processor;

import com.merkleinc.eventsystem.SkyEventNotifier;
import com.merkleinc.eventsystem.model.Event;
import com.merkleinc.eventsystem.model.SkyEventNotification;

public class EventAcceptProcessor implements EventTypeProcessor {

    private SkyEventNotifier skyEventNotifier;

    public EventAcceptProcessor(SkyEventNotifier skyEventNotifier) {
        this.skyEventNotifier = skyEventNotifier;
    }

    @Override
    public void process(Event event) {

        if (event.isAccept()) {

            SkyEventNotification notification = new SkyEventNotification();
            notification.setType("ACCEPT");
            notification.setEventTime(event.getEventTime());
            notification.setEngineerId(event.getEngineerId());
            notification.setVisitNumber(event.getVisitNumber());

            skyEventNotifier.sendNotification(notification);

            // TODO fer que el acccept retorni el visitNumber, i caldra
            // TODO guardarlo al event service per a tenirlo pels seguents events
        }
    }
}
