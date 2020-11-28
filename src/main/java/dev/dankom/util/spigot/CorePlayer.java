package dev.dankom.util.spigot;

import dev.dankom.core.profile.Profile;
import dev.dankom.core.user.UserManager;
import me.clip.placeholderapi.PlaceholderAPI;
import net.minecraft.server.v1_8_R1.EntityPlayer;
import org.bukkit.ChatColor;
import org.bukkit.craftbukkit.v1_8_R1.CraftServer;
import org.bukkit.craftbukkit.v1_8_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class CorePlayer extends CraftPlayer {
    private Client client;
    private String channel;

    public CorePlayer(CraftServer server, EntityPlayer entity) {
        super(server, entity);
    }

    public CorePlayer(Player player) {
        this((CraftServer) player.getServer(), ((CraftPlayer)player).getHandle());
    }

    public CorePlayer(CraftPlayer player) {
        this((CraftServer) player.getServer(), player.getHandle());
    }

    public Profile getCoreProfile() {
        return new Profile(this);
    }

    public CoreHandle getCoreHandle() {
        return new CoreHandle(this);
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Client getClient() {
        return client;
    }

    public String channel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public void unregisterChannel() {
        setClient(Client.VANILLA);
    }

    public void registerChannel() {
        setClient(Client.getClient(channel()));
    }

    public UserManager getUserManager() {
        return UserManager.getInstance();
    }

    @Override
    public void sendMessage(String message) {
        super.sendMessage(ChatColor.translateAlternateColorCodes('&', PlaceholderAPI.setBracketPlaceholders(this, message)));
    }

    public static CorePlayer toCorePlayer(Player player) {
        return new CorePlayer((CraftServer) player.getServer(), ((CraftPlayer)player).getHandle());
    }

    public static CorePlayer toCorePlayer(CraftPlayer player) {
        return new CorePlayer((CraftServer) player.getServer(), player.getHandle());
    }

    public class CoreHandle {
        private Player player;

        public CoreHandle(Player player) {
            this.player = player;
        }

        public EntityPlayer getHandle() {
            return getCraftBukkitReference().getHandle();
        }

        public CorePlayer getCoreReference() {
            return CorePlayer.toCorePlayer(getCraftBukkitReference());
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
}
