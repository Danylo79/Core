package dev.dankom.core.listeners;

import dev.dankom.Start;
import dev.dankom.util.coreHelpers.CorePlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRegisterChannelEvent;
import org.bukkit.event.player.PlayerUnregisterChannelEvent;

public class ClientListener implements Listener {

    private Start plugin = Start.getInstance();

    public void init() {
        // Add our channel listeners
        plugin.getServer().getMessenger().registerOutgoingPluginChannel(plugin, "Lunar-Client");
        plugin.getServer().getMessenger().registerIncomingPluginChannel(plugin, "Lunar-Client", (channel, player, bytes) -> {
//            if (bytes[0] == 26) {
//                final UUID outcoming = BufferUtil.getUUIDFromBytes(Arrays.copyOfRange(bytes, 1, 30));
//
//                // To stop server wide spoofing.
//                if (!outcoming.equals(player.getUniqueId())) {
//                    return;
//                }
//
//                CorePlayer user = CorePlayer.toCorePlayer(player);
//
//                if (user != null && !user.getCoreHandle().isClient(Client.LUNAR)){
//                    user.getClientHelper().setClient(Client.LUNAR);
//                    new AuthenticateEvent(player).call(plugin);
//                }
//
//                for (Player other : Bukkit.getOnlinePlayers()) {
//                    if (user.getClientHelper().isAuthenticated()) {
//                        other.sendPluginMessage(plugin, channel, bytes);
//                    }
//                }
//            }
        });
    }

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
