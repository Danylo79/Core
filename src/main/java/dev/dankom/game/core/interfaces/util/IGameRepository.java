package dev.dankom.game.core.interfaces.util;

import dev.dankom.game.core.interfaces.IGame;

import java.util.List;
import java.util.UUID;

public interface IGameRepository {
    List<IGame> getGames();

    default void addGame(IGame game) {
        IGame g = game;
        if (getGames().contains(g)) {
            getGames().remove(g);
        }
        getGames().add(g);
        g.onStartup();
    }

    default void removeGame(IGame game) {
        IGame g = game;
        getGames().remove(game);
        g.onShutdown();
    }

    default IGame getGame(UUID uuid) {
        for (IGame ig : getGames()) {
            if (ig.getSession().getId().equals(uuid)) {
                return ig;
            } else {
                continue;
            }
        }
        return null;
    }
}
