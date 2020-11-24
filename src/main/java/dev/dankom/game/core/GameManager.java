package dev.dankom.game.core;

import dev.dankom.core.profile.Profile;
import dev.dankom.game.core.game.Game;
import dev.dankom.game.core.game.IMap;
import dev.dankom.game.core.game.gtx.BaseGameContext;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.PlayerDeathEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class GameManager implements Listener {
    public List<Game> games = new ArrayList<>();

    public void createGame(Player p1, Player p2, IMap map) {
        Game e = new Game(new Profile(p1), new Profile(p2), new BaseGameContext(map, this));
        games.add(e);
        e.onStart(e.getP1(), e.getP2());
    }

    public void finishGame(Game game) {
        games.remove(game);
    }

    public Game getGame(UUID uuid) {
        for (Game game : games) {
            if (game.getId() == uuid) {
                return game;
            } else {
                continue;
            }
        }
        return null;
    }

    public Game getGame(String uuid) {
        UUID u = UUID.fromString(uuid);
        for (Game game : games) {
            if (game.getId() == u) {
                return game;
            } else {
                continue;
            }
        }
        return null;
    }

    @EventHandler
    public void onKill(PlayerDeathEvent e) {
        for (Game g : games) {
            g.onKill(e);
        }
    }

    @EventHandler
    public void onBreak(BlockBreakEvent e) {
        for (Game g : games) {
            g.onBreak(e);
        }
    }

    @EventHandler
    public void onPlace(BlockPlaceEvent e) {
        for (Game g : games) {
            g.onPlace(e);
        }
    }
}
