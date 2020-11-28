package dev.dankom.util.spigot;

import dev.dankom.core.user.UserManager;
import net.minecraft.server.v1_8_R1.EntityPlayer;
import org.bukkit.craftbukkit.v1_8_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class CoreHandle {
    private Player player;

    public CoreHandle(Player player) {
        this.player = player;
    }

    public EntityPlayer getHandle() {
        return getCraftBukkitReference().getHandle();
    }

    public CraftPlayer getCraftBukkitReference() {
        return (CraftPlayer) getSpigotReference();
    }

    public Player getSpigotReference() {
        return player;
    }

    public boolean isLunarClient() {
        return getPlayer().getClient() == Client.LUNAR_CLIENT;
    }

    private CorePlayer getPlayer() {
        return UserManager.getInstance().getPlayer(player.getUniqueId());
    }

    public String channel() {
        return getPlayer().channel();
    }
}
