package dev.dankom.game.core.defaults;

import dev.dankom.game.core.interfaces.util.ISpawnpoint;
import dev.dankom.util.coreHelpers.core.CoreLocation;

public class DefaultSpawnpoint implements ISpawnpoint {

    private CoreLocation spawn;
    private int number;

    public DefaultSpawnpoint(CoreLocation spawn, int number) {
        this.spawn = spawn;
        this.number = number;
    }

    @Override
    public CoreLocation getSpawn() {
        return spawn;
    }

    @Override
    public int getNumber() {
        return number;
    }
}
