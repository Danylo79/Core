package dev.dankom.util.spigot;

import dev.dankom.core.profile.Profile;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public abstract class CorePlayer implements Player {
    public void sendFullMsg(String msg) {
        sendMessage(ChatColor.translateAlternateColorCodes('&', PlaceholderAPI.setBracketPlaceholders(this, msg)));
    }

    public Profile getProfile() {
        return new Profile(this);
    }
}
