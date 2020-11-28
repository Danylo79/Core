package dev.dankom.core.listeners;

import dev.dankom.core.profile.Profile;
import dev.dankom.core.user.UserManager;
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
        profile.getProfileManager().refreshPlayer(profile);

        UserManager.getInstance().addPlayer(e.getPlayer());

        for (Profile p : profile.getFriends()) {
            p.player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aFriend > &r" + profile.getRank().getColor() + profile.getName() + " &ejoined the game."));
        }

        for (Profile p : profile.getGuild().getPlayers()) {
            p.player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&2Guild > &r" + profile.getRank().getColor() + profile.getName() + " &ejoined the game."));
        }

        e.setJoinMessage("");
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        Profile profile = new Profile(e.getPlayer());
        profile.getProfileManager().refreshPlayer(profile);

        for (Profile p : profile.getFriends()) {
            p.player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aFriend > &r" + profile.getRank().getColor() + profile.getName() + " &eleft."));
        }

        for (Profile p : profile.getGuild().getPlayers()) {
            p.player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&2Guild > &r" + profile.getRank().getColor() + profile.getName() + " &eleft."));
        }
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) {
        Profile profile = new Profile(e.getPlayer());
        e.setFormat(profile.getLevelTag() + " " + profile.getProfileManager().getChatFormat(e.getMessage(), profile));
        profile.completeAchievement("chat");
    }
}
