package dev.dankom.core.profile.profileManager;

import dev.dankom.core.profile.Profile;
import dev.dankom.core.rank.Rank;
import dev.dankom.util.Actionbar;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public interface IProfileManager {

    void refreshPlayer(Profile profile);

    void refreshLobbyPlayer(Profile profile);

    void setNick(String nick, Profile profile);

    void setRank(Rank rank, Profile profile);

    default void toggleNicked(Profile profile) {
        profile.set("nick.nicked", !((boolean)profile.get("nick.nicked")));
    }

    default void setNicked(boolean nicked, Profile profile) {
        profile.set("nick.nicked", nicked);
    }

    default void sentActionbar(String msg, Profile profile) {
        new Actionbar(msg, profile.player).send();
    }

    default void sendMessage(String msg, Profile profile) {
        profile.player.sendMessage(ChatColor.translateAlternateColorCodes('&', msg));
    }

    default void hideOtherPlayers(Profile profile) {
        for (Player p : Bukkit.getOnlinePlayers()) {
            if (p.getUniqueId() != profile.player.getUniqueId()) {
                profile.player.hidePlayer(p);
            } else {
                continue;
            }
        }
    }

    default void showOtherPlayers(Profile profile) {
        for (Player p : Bukkit.getOnlinePlayers()) {
            if (p.getUniqueId() != profile.player.getUniqueId()) {
                profile.player.showPlayer(p);
            } else {
                continue;
            }
        }
    }
}
