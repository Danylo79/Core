package dev.dankom.game.core.interfaces;

import dev.dankom.game.core.interfaces.util.ISpawnpoint;
import dev.dankom.trigger.Trigger;
import me.arcaniax.hdb.api.HeadDatabaseAPI;
import org.bukkit.Location;
import org.bukkit.event.Event;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.PlayerDeathEvent;

import java.util.UUID;

public interface IGame {
    ISession getSession();
    int getMaxPlayers();
    int getMinPlayers();

    //Util Methods
    default void generateRandomId() {
        getSession().setId(UUID.randomUUID());
    }
    default Location getSpawn(int id) {
        for (ISpawnpoint sp : getSession().getMap().getSpawnpoints()) {
            if (sp.getNumber() == id) {
                return sp.getSpawn();
            } else {
                continue;
            }
        }
        return null;
    }

    //Game Hooks
    default void onStart() {}
    default void onFinish() {}

    //API Hooks
    default HeadDatabaseAPI getHDB() {
        return new HeadDatabaseAPI();
    }

    //Event Hooks
    default void onDeath(PlayerDeathEvent e) {}
    default void onBlockPlace(BlockPlaceEvent e) {}
    default void onBlockBreak(BlockBreakEvent e) {}
    default void onEvent(Event e) {}
    default void onTrigger(Trigger trigger) {}
}
