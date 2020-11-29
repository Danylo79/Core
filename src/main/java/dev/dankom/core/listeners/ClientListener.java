package dev.dankom.core.listeners;

import dev.dankom.Start;
import dev.dankom.core.user.UserManager;
import dev.dankom.util.coreHelpers.Client;
import dev.dankom.util.coreHelpers.CorePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRegisterChannelEvent;
import org.bukkit.event.player.PlayerUnregisterChannelEvent;

public class ClientListener implements Listener {

    private Start plugin = Start.getInstance();

    @EventHandler
    public void onRegisterChannel(PlayerRegisterChannelEvent event) {
        if (event.getPlayer() != null) {
            CorePlayer player = CorePlayer.toCorePlayer(event.getPlayer());
            player.getClientHelper().setup(event.getChannel());
        }
    }

    @EventHandler
    public void onUnregisterChannel(PlayerUnregisterChannelEvent event) {
        if (event.getPlayer() != null) {
            CorePlayer player = CorePlayer.toCorePlayer(event.getPlayer());
            player.getClientHelper().shutdown();
        }
    }
}
