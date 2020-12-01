package dev.dankom.util.coreHelpers;

import dev.dankom.core.lobby.LobbyManager;
import net.minecraft.server.v1_8_R1.WorldServer;
import org.apache.commons.io.FileUtils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.craftbukkit.v1_8_R1.CraftWorld;
import org.bukkit.entity.Player;
import org.bukkit.generator.ChunkGenerator;

import java.io.File;
import java.util.List;

public class CoreWorld extends CraftWorld {
    public CoreWorld(WorldServer world, ChunkGenerator gen, Environment env) {
        super(world, gen, env);
    }

    public World copyWorld(String name, File destDir) {
        try {
            FileUtils.copyDirectory(getWorldFolder(), destDir);
            WorldCreator worldCreator = new WorldCreator(name);
            return worldCreator.createWorld();
        } catch (Exception e) {
            return null;
        }
    }

    public CoreHandle getCoreHandle() {
        return new CoreHandle(this);
    }

    public World duplicateWorld(String name) {
        return copyWorld(name, getWorldFolder().getParentFile());
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

    public boolean connectPlayer(CorePlayer player, double x, double y, double z) {
        try {
            player.sendMessage("&aSending you to &b" + getName() + "&b!");
            player.teleport(new Location(this, x, y, z));
            return true;
        } catch (Exception e) {
            player.sendMessage("&cFailed to send you to server! Wait a bit and try again. If this error is persistent contact an admin.");
            return false;
        }
    }

    public boolean isLobby() {
        List<String> lobbies = LobbyManager.database().getStringList("lobbies");
        return lobbies.contains(getName());
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
