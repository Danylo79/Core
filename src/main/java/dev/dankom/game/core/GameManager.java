package dev.dankom.game.core;

import dev.dankom.game.core.interfaces.IGame;
import dev.dankom.game.core.interfaces.util.IGameRepository;
import org.bukkit.event.Listener;

import java.util.ArrayList;
import java.util.List;

public class GameManager implements Listener {
    private static GameManager instance;

    private static IGameRepository gameRepository;

    public GameManager() {
        instance = this;
        gameRepository = new DefaultGameRepository();
    }

    public IGameRepository getGameRepository() {
        return gameRepository;
    }

    public static GameManager getInstance() {
        if (instance == null) {
            instance = new GameManager();
        }
        return instance;
    }

    public class DefaultGameRepository implements IGameRepository {

        private List<IGame> games;

        public DefaultGameRepository() {
            this.games = new ArrayList<>();
        }

        @Override
        public List<IGame> getGames() {
            return games;
        }
    }
}
