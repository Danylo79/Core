package dev.dankom.core.listeners;

import dev.dankom.Start;
import dev.dankom.core.event.AuthenticateEvent;
import dev.dankom.core.user.UserManager;
import dev.dankom.util.BufferUtil;
import dev.dankom.util.spigot.Client;
import dev.dankom.util.spigot.CorePlayer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRegisterChannelEvent;
import org.bukkit.event.player.PlayerUnregisterChannelEvent;

import java.util.Arrays;
import java.util.UUID;

public class ClientListener implements Listener {

    public void init() {
        // Add our channel listeners
        Bukkit.getServer().getMessenger().registerOutgoingPluginChannel(Start.getInstance(), "Lunar-Client");
        Bukkit.getServer().getMessenger().registerIncomingPluginChannel(Start.getInstance(), "Lunar-Client", (channel, player, bytes) -> {
            if (bytes[0] == 26) {
                final UUID outcoming = BufferUtil.getUUIDFromBytes(Arrays.copyOfRange(bytes, 1, 30));

                // To stop server wide spoofing.
                if (!outcoming.equals(player.getUniqueId())) {
                    return;
                }

                CorePlayer user = UserManager.getInstance().getPlayer(outcoming);

                if (user != null && !user.getCoreHandle().isLunarClient()){
                    user.setClient(Client.LUNAR_CLIENT);
                    new AuthenticateEvent(player).call(Start.getInstance());
                }

                for (Player other : Bukkit.getOnlinePlayers()) {
                    if (isAuthenticated(other)) {
                        other.sendPluginMessage(Start.getInstance(), channel, bytes);
                    }
                }
            }
        });
    }

    @EventHandler
    public void onRegisterChannel(PlayerRegisterChannelEvent event){
        CorePlayer player = UserManager.getInstance().getPlayer(event.getPlayer().getUniqueId());
        player.setChannel(event.getChannel());
        player.registerChannel();
    }

    @EventHandler
    public void onUnregisterChannel(PlayerUnregisterChannelEvent event) {
        CorePlayer player = UserManager.getInstance().getPlayer(event.getPlayer().getUniqueId());
        player.setChannel(event.getChannel());
        player.unregisterChannel();
    }

    public boolean isAuthenticated(Player player) {
        CorePlayer user = UserManager.getInstance().getPlayer(player.getUniqueId());

        if (user == null){
            return false;
        }

        return user.getCoreHandle().isLunarClient();
    }
}
