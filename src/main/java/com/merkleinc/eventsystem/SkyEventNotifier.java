package com.merkleinc.eventsystem;

import com.merkleinc.eventsystem.model.SkyEventNotification;

public class SkyEventNotifier {

    public void sendNotification(SkyEventNotification notification) {
        System.out.println(
                String.format("%s notification for visit %s processed",
                        notification.getType(),
                        notification.getVisitNumber()));
    }
}
