package dev.dankom.util.spigot;

import net.minecraft.server.v1_8_R1.EntityPlayer;
import org.bukkit.craftbukkit.v1_8_R1.CraftServer;
import org.bukkit.craftbukkit.v1_8_R1.entity.CraftPlayer;

public class CorePlayer extends CraftPlayer {
    public CorePlayer(CraftServer server, EntityPlayer entity) {
        super(server, entity);
    }

    public CoreHandle getCoreHandle() {
        return new CoreHandle(this);
    }
}
