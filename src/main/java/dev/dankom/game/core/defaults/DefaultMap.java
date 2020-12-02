package dev.dankom.game.core.defaults;

import dev.dankom.game.core.interfaces.IMap;
import dev.dankom.game.core.interfaces.util.ISpawnpoint;
import dev.dankom.util.ListHelper;
import dev.dankom.util.coreHelpers.core.CoreWorld;

import java.util.List;

public class DefaultMap implements IMap {

    private CoreWorld baseWorld;
    private List<ISpawnpoint> spawnpoints;
    private boolean copyOnStart;

    public DefaultMap(CoreWorld baseWorld, boolean copyOnStart, ISpawnpoint... spawnpoints) {
        this.baseWorld = baseWorld;
        this.spawnpoints = ListHelper.toList(spawnpoints);
        this.copyOnStart = copyOnStart;
    }

    @Override
    public List<ISpawnpoint> getSpawnpoints() {
        return spawnpoints;
    }

    @Override
    public CoreWorld getBaseWorld() {
        return baseWorld;
    }

    @Override
    public boolean copyOnStart() {
        return copyOnStart;
    }
}
