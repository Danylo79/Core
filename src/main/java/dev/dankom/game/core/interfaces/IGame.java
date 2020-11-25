package dev.dankom.game.core.interfaces;

import dev.dankom.trigger.Trigger;
import org.bukkit.event.Event;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.PlayerDeathEvent;

import java.util.UUID;

public interface IGame {
    ISession getSession();

    //Util Methods
    default void generateRandomId() {
        getSession().setId(UUID.randomUUID());
    }

    //Game Hooks
    default void onStart() {}
    default void onFinish() {}

    //Event Hooks
    default void onDeath(PlayerDeathEvent e) {}
    default void onBlockPlace(BlockPlaceEvent e) {}
    default void onBlockBreak(BlockBreakEvent e) {}
    default void onEvent(Event e) {}
    default void onTrigger(Trigger trigger) {}
}
