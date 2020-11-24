package dev.dankom.core.listeners;

import dev.dankom.trigger.triggers.EventTrigger;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;

public class Listener implements org.bukkit.event.Listener {
    @EventHandler
    public void onEvent(Event event) {
        new EventTrigger(event);
    }
}
