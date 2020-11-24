package dev.dankom.game.core.game;

import org.bukkit.Location;
import org.bukkit.World;

import java.io.File;

public interface IMap {
    String getName();
    World getWorld();
    boolean copyOnCreate();
    Location getP1Spawn();
    Location getP2Spawn();
    File getFile();
}
