package dev.dankom.core.listeners;

import dev.dankom.core.profile.Profile;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class ProfileListener implements Listener {
    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Profile profile = new Profile(e.getPlayer());
        profile.addPlayerData();
        profile.update();
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        Profile profile = new Profile(e.getPlayer());
        profile.addPlayerData();
        profile.update();
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) {
        Profile profile = new Profile(e.getPlayer());
        e.setFormat(ChatColor.translateAlternateColorCodes('&', profile.getFullName() + "&f: " + e.getMessage()));
    }
}
