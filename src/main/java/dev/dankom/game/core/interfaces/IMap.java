package dev.dankom.game.core.interfaces;

import dev.dankom.game.core.interfaces.util.ISpawnpoint;
import dev.dankom.util.coreHelpers.core.CoreWorld;

import java.util.List;

public interface IMap {
    List<ISpawnpoint> getSpawnpoints();
    CoreWorld getBaseWorld();
    boolean copyOnStart();
}
