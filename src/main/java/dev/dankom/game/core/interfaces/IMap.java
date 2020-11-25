package dev.dankom.game.core.interfaces;

import dev.dankom.game.core.util.Spawnpoint;
import dev.dankom.util.CoreWorld;

import java.util.List;

public interface IMap {
    List<Spawnpoint> getSpawnpoints();
    CoreWorld getBaseWorld();
    boolean getCopyOnStart();
}
