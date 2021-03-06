package dev.dankom.core.event;

import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.java.JavaPlugin;

import java.awt.*;

public class BaseEvent extends Event {

    private static final HandlerList handlers = new HandlerList();

    public static HandlerList getHandlerList() {
        return handlers;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public boolean call(JavaPlugin javaPlugin) {
        javaPlugin.getServer().getPluginManager().callEvent(this);
        return this instanceof Cancellable && ((Cancellable) this).isCancelled();
    }
}
