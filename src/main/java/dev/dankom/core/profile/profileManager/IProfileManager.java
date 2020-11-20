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

    default void refreshPlayer(boolean lobby, Profile profile) {
        if (lobby) {
            profile.getProfileManager().refreshLobbyPlayer(profile);
        } else {
            profile.getProfileManager().refreshPlayer(profile);
        }
    }

    void setNick(String nick, Profile profile);

    void setRank(Rank rank, Profile profile);

    default boolean inLobby(Profile profile) {
        return (boolean) profile.database().get("network.lobby");
    }

    default void setInLobby(boolean b, Profile profile) {
        profile.database().set("network.lobby", b);
    }

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

    default void giveCoins(int amt, boolean message, Profile profile) {
        int coins = (int) profile.get("coins");
        double rankMultiplier = profile.getRank().getMultiplier();
        double lvlMultiplier = getLevelMultiplier(profile);
        double add = coins + (amt * (rankMultiplier + lvlMultiplier));
        if (message) {
            sentActionbar("&6+" + add + "(&b" + rankMultiplier + "&6x rank multiplier) (&b" + lvlMultiplier + "&6x level multiplier)", profile);
            sendMessage("&6+" + add + "(&b" + rankMultiplier + "&6x rank multiplier) (&b" + lvlMultiplier + "&6x level multiplier)", profile);
        }
        profile.set("coins", add);
    }

    default void giveXp(int amt, boolean message, Profile profile) {
        int xp = (int) profile.get("network.xp");
        double rankMultiplier = profile.getRank().getMultiplier();
        double lvlMultiplier = getLevelMultiplier(profile);
        double add = xp + (amt * (rankMultiplier + lvlMultiplier));
        if (message) {
            sentActionbar("&6+" + add + "(&b" + rankMultiplier + "&6x rank multiplier) (&b" + lvlMultiplier + "&6x level multiplier)", profile);
            sendMessage("&6+" + add + "(&b" + rankMultiplier + "&6x rank multiplier) (&b" + lvlMultiplier + "&6x level multiplier)", profile);
        }
        profile.set("network.xp", add);
    }

    default double getLevelMultiplier(Profile profile) {
        int level = (int) profile.get("network.level");
        boolean is50 = level >= 50;
        boolean is100 = level >= 100;
        boolean is150 = level >= 150;
        boolean is200 = level >= 200;

        if (is50 && !is100) {
            return 2.0;
        } else if (is100 && !is150) {
            return 2.5;
        } else if (is150 && !is200) {
            return 3.0;
        } else {
            return 4.0;
        }
    }

    String getChatFormat(String msg, Profile profile);
}
