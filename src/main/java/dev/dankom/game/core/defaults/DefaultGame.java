package dev.dankom.game.core.defaults;

import dev.dankom.core.profile.Profile;
import dev.dankom.game.core.GameManager;
import dev.dankom.game.core.interfaces.IGame;
import dev.dankom.game.core.interfaces.IMap;
import dev.dankom.game.core.interfaces.ISession;
import dev.dankom.game.core.interfaces.util.ISpawnpoint;
import dev.dankom.logger.LogLevel;
import dev.dankom.logger.Logger;
import dev.dankom.util.coreHelpers.CoreLocation;
import dev.dankom.util.coreHelpers.CorePlayer;
import dev.dankom.util.coreHelpers.CoreWorld;

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
            onStart();
        } catch (Exception e) {
            failSafe();
        }
    }

    public void failSafe() {
        //Fail Safe encase of error
        Logger.log(LogLevel.ERROR, "Failed to start game! Id: " + getSession().getId());
        for (Profile p : getSession().getPlayers()) {
            CorePlayer cp = p.getPlayer();
            p.set("network.game.id", "");
            cp.sendMessage("&cFailed to start game! Wait a bit and try again!");
            cp.kickPlayer("&cFailed to start game! Sorry for the inconvenience. Game Id: (&b" + getSession().getId() + "&c)");
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
