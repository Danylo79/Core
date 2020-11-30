package dev.dankom.game.core.interfaces.util;

import dev.dankom.util.coreHelpers.CoreLocation;
import org.bukkit.Location;

public interface ISpawnpoint {
    CoreLocation getSpawn();
    int getNumber();
}
