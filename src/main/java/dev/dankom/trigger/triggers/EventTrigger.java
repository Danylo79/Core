package dev.dankom.trigger.triggers;

import dev.dankom.trigger.Trigger;
import org.bukkit.event.Event;

public class EventTrigger extends Trigger {
    private String eventName;
    private Event event;

    public EventTrigger(Event event) {
        super("event");
        this.event = event;
        this.eventName = event.getEventName();
    }

    public Event getEvent() {
        return event;
    }

    public String getEventName() {
        return eventName;
    }
}
