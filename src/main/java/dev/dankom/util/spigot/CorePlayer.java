package dev.dankom.util.spigot;

import dev.dankom.core.profile.Profile;
import org.bukkit.craftbukkit.v1_8_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

import java.util.UUID;

public class CorePlayer {
    private Client client;
    private String channel;
    private CraftPlayer player;

    public CorePlayer(CraftPlayer player) {
        this.player = player;
    }
    public CorePlayer(Player player) {
        this.player = (CraftPlayer) player;
    }

    public Profile getProfile() {
        return new Profile(player);
    }

    public CoreHandle getCoreHandle() {
        return new CoreHandle(player);
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
        player.sendMessage(channel);
    }

    public UUID getUniqueId() {
        return player.getUniqueId();
    }

    public void unregisterChannel() {
        setClient(Client.VANILLA);
    }

    public void registerChannel() {
        setClient(Client.getClient(channel()));
    }
}
