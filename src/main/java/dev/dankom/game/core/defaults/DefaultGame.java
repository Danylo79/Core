package dev.dankom.game.core.defaults;

import dev.dankom.core.Triggers;
import dev.dankom.core.lobby.LobbyManager;
import dev.dankom.core.profile.Profile;
import dev.dankom.game.core.interfaces.IGame;
import dev.dankom.game.core.interfaces.IMap;
import dev.dankom.game.core.interfaces.ISession;
import dev.dankom.game.core.interfaces.util.ISpawnpoint;
import dev.dankom.logger.LogLevel;
import dev.dankom.logger.Logger;
import dev.dankom.trigger.triggers.game.EndGameTrigger;
import dev.dankom.trigger.triggers.game.FailGameTrigger;
import dev.dankom.trigger.triggers.game.StartGameTrigger;
import dev.dankom.util.Validation;
import dev.dankom.util.coreHelpers.CoreLocation;
import dev.dankom.util.coreHelpers.CorePlayer;
import dev.dankom.util.coreHelpers.CoreWorld;
import org.bukkit.Bukkit;

public class DefaultGame implements IGame {

    protected ISession session;
    protected int maxPlayers;
    protected int minPlayers;
    protected CoreWorld world;
    protected IMap map;

    public DefaultGame(int maxPlayers, int minPlayers, IMap map) {
        this.maxPlayers = maxPlayers;
        this.minPlayers = minPlayers;
        this.map = map;
        this.session = new DefaultSession(this, this.map);
    }

    @Override
    public void onStartup() {
        generateRandomId();
        try {
            if (getSession().getMap().copyOnStart()) {
                CoreWorld baseWorld = getSession().getMap().getBaseWorld();
                CoreWorld newWorld = CoreWorld.toCoreWorld(baseWorld.duplicateWorld(baseWorld.getName() + "-" + session.getId()));
                for (int i = 0; i < getSession().getPlayers().size(); i++) {
                    Profile p = getSession().getPlayers().get(i);
                    ISpawnpoint spawnpoint = getSession().getMap().getSpawnpoints().get(i);
                    CoreLocation loc = spawnpoint.getSpawn();
                    newWorld.connectPlayer(p.getPlayer(), loc.getX(), loc.getY(), loc.getZ());
                    this.world = newWorld;

                    p.set("network.game.id", getSession().getId().toString());
                }
            }
            new StartGameTrigger(this);
            onStart();
        } catch (Exception e) {
            failSafe("&cFailed to start game! Sorry for the inconvenience. Game Id: (&b" + getSession().getId() + "&c)");
        }
    }

    @Override
    public void onShutdown() {
        try {
            for (Profile p : getSession().getPlayers()) {
                CorePlayer cp = p.getPlayer();
                p.set("network.game.id", "");
                teleportToLobby(cp);
                new EndGameTrigger(this);
            }
        } catch (Exception e) {
            failSafe("&cFailed to stop game! Sorry for the inconvenience. Game Id: (&b" + getSession().getId() + "&c)");
        }
    }

    public void failSafe(String message) {
        //Fail Safe encase of error
        Logger.log(LogLevel.ERROR, "Failed to start game! Id: " + getSession().getId());
        for (Profile p : getSession().getPlayers()) {
            CorePlayer cp = p.getPlayer();
            p.set("network.game.id", "");
            cp.sendMessage(message);
            teleportToLobby(cp);
        }
        new FailGameTrigger(this);
    }

    public boolean teleportToLobby(CorePlayer player) {
        try {
            CoreWorld lobby = CoreWorld.toCoreWorld(Bukkit.getWorld(LobbyManager.database().getStringList("lobbies").get(0)));
            lobby.connectPlayer(player, LobbyManager.database().getInt("x"), LobbyManager.database().getInt("y"), LobbyManager.database().getInt("z"));
            return true;
        } catch (Exception e) {
            Validation.throwCompactException("Failed to teleport player to lobby! Kicking player to avoid breaking.");
            player.kickPlayer("&cFailed to connect to server!");
            return false;
        }
    }

    @Override
    public ISession getSession() {
        return session;
    }

    @Override
    public int getMaxPlayers() {
        return maxPlayers;
    }

    @Override
    public int getMinPlayers() {
        return minPlayers;
    }

    public CoreWorld getWorld() {
        return world;
    }
}
