package dev.dankom.game.core.interfaces;

import com.comphenix.protocol.ProtocolConfig;
import com.comphenix.protocol.ProtocolLib;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import dev.dankom.game.core.interfaces.util.ISpawnpoint;
import dev.dankom.trigger.Trigger;
import me.arcaniax.hdb.api.HeadDatabaseAPI;
import me.clip.placeholderapi.PlaceholderAPI;
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
    default UUID getId() {
        return getSession().getId();
    }

    //Game Hooks
    default void onStart() {}
    default void onFinish() {}

    //API Hooks
    default HeadDatabaseAPI getHDB() {
        return new HeadDatabaseAPI();
    }
    //ProtocolLib
    default ProtocolManager getProtocolManager() {
        return ProtocolLibrary.getProtocolManager();
    }
    default ProtocolConfig getProtocolConfig() {
        return ProtocolLibrary.getConfig();
    }

    //Event Hooks
    default void onDeath(PlayerDeathEvent e) {}
    default void onBlockPlace(BlockPlaceEvent e) {}
    default void onBlockBreak(BlockBreakEvent e) {}
    default void onEvent(Event e) {}
    default void onTrigger(Trigger trigger) {}

    //Util Hooks
    default void onStartup() {}
    default void onShutdown() {}
}
