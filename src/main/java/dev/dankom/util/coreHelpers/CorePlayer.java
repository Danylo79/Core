package dev.dankom.util.coreHelpers;

import dev.dankom.core.profile.Profile;
import dev.dankom.core.user.UserManager;
import dev.dankom.logger.LogLevel;
import dev.dankom.logger.Logger;
import dev.dankom.util.Validation;
import me.clip.placeholderapi.PlaceholderAPI;
import net.minecraft.server.v1_8_R1.EntityPlayer;
import org.bukkit.ChatColor;
import org.bukkit.craftbukkit.v1_8_R1.CraftServer;
import org.bukkit.craftbukkit.v1_8_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class CorePlayer extends CraftPlayer {
    private String channel;
    private ClientHelper clientHelper;

    public CorePlayer(CraftServer server, EntityPlayer entity) {
        super(server, entity);

        if (!getUserManager().contains(this)) {
            getUserManager().addPlayer(getCoreHandle().getSpigotReference());
        } else {
            getUserManager().updatePlayer(getUniqueId(), this);
        }
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

    public ClientHelper getClientHelper() {
        if (clientHelper == null) {
            clientHelper = new ClientHelper(this);
        }
        return clientHelper;
    }

    public UserManager getUserManager() {
        return UserManager.getInstance();
    }

    @Override
    public void sendMessage(String message) {
        super.sendMessage(ChatColor.translateAlternateColorCodes('&', PlaceholderAPI.setBracketPlaceholders(this, message)));
    }

    public static CorePlayer toCorePlayer(Player player) {
        return toCorePlayer((CraftPlayer) player);
    }

    public static CorePlayer toCorePlayer(CraftPlayer player) {
        Validation.notNull("Player cannot be null!", player);
        Validation.notNull("UserManager cannot be null!", UserManager.getInstance());

        CorePlayer corePlayer;
        if (!UserManager.getInstance().contains(player)) {
            corePlayer = new CorePlayer((CraftServer) player.getServer(), player.getHandle());
        } else {
            CraftPlayer p = (CraftPlayer) UserManager.getInstance().getPlayer(player.getUniqueId());
            corePlayer = new CorePlayer((CraftServer) p.getServer(), p.getHandle());
        }
        Validation.notNull("Failed to set corePlayer!", corePlayer);
        return corePlayer;
    }

    @Override
    public String toString() {
        String out = "CraftPlayer={" + getCoreHandle().getCraftBukkitReference().toString() + ", UUID={" + getUniqueId().toString() + "}, Profile={" + getCoreProfile().getFullName() + "}";
        return out;
    }

    public void refresh() {
        getUserManager().updatePlayer(getUniqueId(), this);
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

        public boolean isClient(Client client) {
            return getPlayer().getClientHelper().getClient() == client;
        }

        private CorePlayer getPlayer() {
            return CorePlayer.toCorePlayer(UserManager.getInstance().getPlayer(player().getUniqueId()));
        }

        public String channel() {
            return getPlayer().getClientHelper().channel();
        }

        public CorePlayer player() {
            return CorePlayer.toCorePlayer(UserManager.getInstance().getPlayer(player.getUniqueId()));
        }
    }

    public class ClientHelper {
        private CorePlayer player;
        private String channel;
        private Client client;

        public ClientHelper(Player player) {
            this.player = CorePlayer.toCorePlayer(player);
            this.client = Client.NOT_SET;
        }

        public void setClient(Client client) {
            setChannel(client.getChannel());
            if (this.client == Client.NOT_SET) {
                this.client = client;
            }
            Logger.log(LogLevel.INFO, "&aSet &b" + getDisplayName() + "&a client to &b" + client.getName() + "&a! Using the [&b" + client.getChannel() + "&a] channel!");
            player().refresh();
        }

        public Client getClient() {
            return client;
        }

        public String channel() {
            return channel;
        }

        public void setChannel(String channel) {
            this.channel = channel;
            player().refresh();
        }

        public void setup(String channel) {
            setChannel(channel);
            setClient(Client.getClient(channel()));
            player().refresh();
        }

        public void shutdown() {
            setClient(Client.NOT_SET);
            player().getUserManager().updatePlayer(player().getUniqueId(), player());
            player().refresh();
        }

        public CorePlayer player() {
            return CorePlayer.toCorePlayer(UserManager.getInstance().getPlayer(player.getUniqueId()));
        }
    }
}
