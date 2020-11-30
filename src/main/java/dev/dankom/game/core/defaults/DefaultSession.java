package dev.dankom.game.core.defaults;

import dev.dankom.core.profile.Profile;
import dev.dankom.game.core.GameManager;
import dev.dankom.game.core.interfaces.IGame;
import dev.dankom.game.core.interfaces.IMap;
import dev.dankom.game.core.interfaces.ISession;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class DefaultSession implements ISession {

    private final GameManager gameManager;
    private final IGame parent;
    private final IMap map;
    private List<Profile> players;
    private UUID id;

    public DefaultSession(GameManager gameManager, IGame parent, IMap map) {
        this.gameManager = gameManager;
        this.parent = parent;
        this.map = map;
        this.players = new ArrayList<>();
    }

    @Override
    public UUID getId() {
        return id;
    }

    @Override
    public List<Profile> getPlayers() {
        return players;
    }

    @Override
    public void setId(UUID uuid) {
        this.id = uuid;
    }

    @Override
    public IGame getParent() {
        return parent;
    }

    @Override
    public IMap getMap() {
        return map;
    }

    @Override
    public GameManager getGameManager() {
        return gameManager;
    }
}
