package com.merkleinc.eventsystem;

import java.util.Date;
import org.junit.Assert;
import org.junit.Test;
import com.merkleinc.eventsystem.model.Event;
import com.merkleinc.eventsystem.utlis.EventServiceStub;
import com.merkleinc.eventsystem.utlis.SkyEventNotifierSpy;

public class EventProcessorTest {

    @Test
    public void noEventsRetrieved() {

        SkyEventNotifierSpy skyEventNotifierSpy = new SkyEventNotifierSpy();

        EventProcessor testee = new EventProcessor(
                new EventServiceStub(),
                skyEventNotifierSpy);

        testee.process();

        Assert.assertFalse(skyEventNotifierSpy.hasBeenCalled());
    }

    @Test
    public void incorrectEventRetrievedDoesNotCallEventNotifier() {

        SkyEventNotifierSpy skyEventNotifierSpy = new SkyEventNotifierSpy();

        EventProcessor testee = new EventProcessor(
                new EventServiceStub(new Event("id", "incorrect", new Date(), "engineerId", "visitNumber")),
                skyEventNotifierSpy);

        testee.process();

        Assert.assertFalse(skyEventNotifierSpy.hasBeenCalled());
    }

}