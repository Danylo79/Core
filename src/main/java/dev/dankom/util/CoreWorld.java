package dev.dankom.util;

import org.apache.commons.io.FileUtils;
import org.bukkit.World;

import java.io.File;
import java.io.IOException;

public class CoreWorld {
    private final World bukkitWorld;

    public CoreWorld(World bukkitWorld) {
        this.bukkitWorld = bukkitWorld;
    }

    public boolean copy(File destDir) {
        try {
            FileUtils.copyDirectory(getBukkitWorld().getWorldFolder(), destDir);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public World getBukkitWorld() {
        return bukkitWorld;
    }
}
