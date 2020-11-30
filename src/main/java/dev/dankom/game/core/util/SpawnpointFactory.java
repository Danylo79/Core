package dev.dankom.game.core.util;

import dev.dankom.game.core.defaults.DefaultSpawnpoint;
import dev.dankom.game.core.interfaces.util.ISpawnpoint;
import dev.dankom.util.coreHelpers.CoreLocation;

public class SpawnpointFactory {
    public static ISpawnpoint createSpawnpoint(double x, double y, double z, int id) {
        return new DefaultSpawnpoint(new CoreLocation(x, y, z), id);
    }

    public static ISpawnpoint createSpawnpoint(Integer x, Integer y, Integer z, int id) {
        return new DefaultSpawnpoint(new CoreLocation(x.doubleValue(), y.doubleValue(), z.doubleValue()), id);
    }
}
