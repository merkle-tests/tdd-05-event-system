package com.merkleinc.eventsystem;

import java.util.Date;
import java.util.List;
import com.merkleinc.eventsystem.eventService.EventService;
import com.merkleinc.eventsystem.model.Event;
import com.merkleinc.eventsystem.processor.EventAcceptProcessor;
import com.merkleinc.eventsystem.processor.EventCompleteProcessor;
import com.merkleinc.eventsystem.processor.EventNotDoneProcessor;
import com.merkleinc.eventsystem.processor.EventRouteProcessor;
import com.merkleinc.eventsystem.processor.EventStartProcessor;
import com.merkleinc.eventsystem.processor.EventTypeProcessor;

public class EventProcessor {

    private final EventService eventService;
    private final EventTypeProcessor acceptProcessor;
    private final EventTypeProcessor routeProcessor;
    private final EventTypeProcessor startProcessor;
    private final EventTypeProcessor completeProcessor;
    private final EventTypeProcessor notDoneProcessor;

    public EventProcessor(EventService eventService,
                          SkyEventNotifier skyEventNotifier) {
        this.eventService = eventService;
        this.acceptProcessor = new EventAcceptProcessor(skyEventNotifier);
        this.routeProcessor = new EventRouteProcessor(skyEventNotifier);
        this.startProcessor = new EventStartProcessor(skyEventNotifier);
        this.completeProcessor = new EventCompleteProcessor(skyEventNotifier);
        this.notDoneProcessor = new EventNotDoneProcessor(skyEventNotifier);
    }

    public void process() {

        List<Event> events = eventService.getEvents(new Date());

        for (Event event : events) {
            acceptProcessor.process(event);
            routeProcessor.process(event);
            startProcessor.process(event);
            completeProcessor.process(event);
            notDoneProcessor.process(event);
        }
    }
}
