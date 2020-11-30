package dev.dankom.util.coreHelpers;

import net.minecraft.server.v1_8_R1.WorldServer;
import org.apache.commons.io.FileUtils;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.craftbukkit.v1_8_R1.CraftWorld;
import org.bukkit.generator.ChunkGenerator;

import java.io.File;

public class CoreWorld extends CraftWorld {
    public CoreWorld(WorldServer world, ChunkGenerator gen, Environment env) {
        super(world, gen, env);
    }

    public boolean copyWorld(File destDir) {
        try {
            FileUtils.copyDirectory(getWorldFolder(), destDir);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public CoreHandle getCoreHandle() {
        return new CoreHandle(this);
    }

    public boolean duplicateWorld() {
        return copyWorld(getWorldFolder().getParentFile());
    }

    public boolean delete() {
        try {
            Bukkit.getWorlds().remove(this);
            FileUtils.deleteDirectory(getWorldFolder());
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static CoreWorld toCoreWorld(World world) {
        return toCoreWorld((CraftWorld) world);
    }

    public static CoreWorld toCoreWorld(CraftWorld world) {
        return new CoreWorld(world.getHandle(), world.getGenerator(), world.getEnvironment());
    }

    public class CoreHandle {
        private CoreWorld world;

        public CoreHandle(CoreWorld world) {
            this.world = world;
        }

        public WorldServer getHandle() {
            return getCraftBukkitReference().getHandle();
        }

        public CoreWorld getCoreReference() {
            return CoreWorld.toCoreWorld(getCraftBukkitReference());
        }

        public CraftWorld getCraftBukkitReference() {
            return (CraftWorld) getSpigotReference();
        }

        public World getSpigotReference() {
            return world;
        }
    }
}
