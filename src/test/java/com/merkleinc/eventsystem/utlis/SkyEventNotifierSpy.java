package com.merkleinc.eventsystem.utlis;

import java.util.ArrayList;
import java.util.List;
import com.merkleinc.eventsystem.SkyEventNotifier;
import com.merkleinc.eventsystem.model.SkyEventNotification;

public class SkyEventNotifierSpy extends SkyEventNotifier {

    private final List<SkyEventNotification> notificationsProcessed;
    private boolean called;

    public SkyEventNotifierSpy() {
        notificationsProcessed = new ArrayList<SkyEventNotification>();
    }

    @Override
    public void sendNotification(SkyEventNotification notification) {
        this.called = true;
        notificationsProcessed.add(notification);
    }

    public boolean hasBeenCalled() {
        return called;
    }

    public List<SkyEventNotification> getNotificationsProcessed() {
        return notificationsProcessed;
    }
}
