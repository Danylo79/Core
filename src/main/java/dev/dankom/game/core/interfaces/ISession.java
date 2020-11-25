package dev.dankom.game.core.interfaces;

import dev.dankom.core.profile.Profile;
import dev.dankom.game.core.GameManager;

import java.util.List;
import java.util.UUID;

public interface ISession {
    UUID getId();
    List<Profile> getPlayers();

    void setId(UUID uuid);

    IGame getParent();
    IMap getMap();
    GameManager getGameManager();
}
