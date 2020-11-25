package dev.dankom.game.core.util;

import org.bukkit.Location;
import org.bukkit.World;

public class Spawnpoint {
    private final int spawnNumber;
    private final Location spawnLoc;

    public Spawnpoint(World world, int x, int y, int z, int spawnNumber) {
        this.spawnNumber = spawnNumber;
        this.spawnLoc = new Location(world, x, y, z);
    }

    public int getSpawnNumber() {
        return spawnNumber;
    }

    public Location getSpawnLoc() {
        return spawnLoc;
    }
}
