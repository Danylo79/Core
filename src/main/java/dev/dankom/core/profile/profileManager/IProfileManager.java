package dev.dankom.core.profile.profileManager;

import dev.dankom.core.profile.Profile;
import dev.dankom.core.rank.Rank;
import dev.dankom.util.Actionbar;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

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
        return (boolean) profile.get("network.lobby");
    }

    default void setInLobby(boolean b, Profile profile) {
        profile.set("network.lobby", b);
    }

    default void toggleNicked(Profile profile) {
        profile.set("nick.nicked", !((boolean)profile.get("nick.nicked")));
    }

    default void setNicked(boolean nicked, Profile profile) {
        profile.set("nick.nicked", nicked);
    }

    default void sendActionbar(String msg, Profile profile) {
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
        double coins;
        if (profile.get("coins") instanceof Integer) {
            coins = ((Integer) profile.get("coins")).doubleValue();
        } else {
            coins = (double) profile.get("coins");
        }
        double rankMultiplier = profile.getRank().getMultiplier();
        double lvlMultiplier = getLevelMultiplier(profile);
        double add = coins + (amt * (rankMultiplier + lvlMultiplier));
        String sAdd = String.format("%.2f", add);
        if (message) {
            sendActionbar("&6+" + sAdd + " (&b" + rankMultiplier + "&6x rank multiplier) (&b" + lvlMultiplier + "&6x level multiplier)", profile);
            sendMessage("&6+" + sAdd + " (&b" + rankMultiplier + "&6x rank multiplier) (&b" + lvlMultiplier + "&6x level multiplier)", profile);
        }
        profile.set("coins", add);
        checkCoinMaster(profile);
    }

    default void giveCoins(int amt, boolean message, String reason, Profile profile) {
        double coins;
        if (profile.get("coins") instanceof Integer) {
            coins = ((Integer) profile.get("coins")).doubleValue();
        } else {
            coins = (double) profile.get("coins");
        }
        double rankMultiplier = profile.getRank().getMultiplier();
        double lvlMultiplier = getLevelMultiplier(profile);
        double add = coins + (amt * (rankMultiplier + lvlMultiplier));
        String sAdd = String.format("%.2f", add);
        if (message) {
            sendActionbar("&6+" + sAdd + " (&b" + rankMultiplier + "&6x rank multiplier) (&b" + lvlMultiplier + "&6x level multiplier) (" + reason + ")", profile);
            sendMessage("&6+" + sAdd + " (&b" + rankMultiplier + "&6x rank multiplier) (&b" + lvlMultiplier + "&6x level multiplier) (" + reason + ")", profile);
        }
        profile.set("coins", add);
        checkCoinMaster(profile);
    }

    default void checkCoinMaster(Profile profile) {
        double coins;
        if (profile.get("coins") instanceof Integer) {
            coins = ((Integer) profile.get("coins")).doubleValue();
        } else {
            coins = (double) profile.get("coins");
        }
        if (coins >= 1000) {
            profile.completeAchievement("coin_master_I");
        }

        if (coins >= 10000) {
            profile.completeAchievement("coin_master_II");
        }

        if (coins >= 100000) {
            profile.completeAchievement("coin_master_III");
        }
    }

    default void giveXp(int amt, boolean message, Profile profile) {
        double xp;
        if (profile.get("network.xp") instanceof Integer) {
            xp = ((Integer) profile.get("network.xp")).doubleValue();
        } else {
            xp = (double) profile.get("network.xp");
        }
        double rankMultiplier = profile.getRank().getMultiplier();
        double lvlMultiplier = getLevelMultiplier(profile);
        double add = xp + (amt * (rankMultiplier + lvlMultiplier));
        String sAdd = String.format("%.2f", add);
        if (message) {
            sendActionbar("&3+" + sAdd + " (&b" + rankMultiplier + "&3x rank multiplier) (&b" + lvlMultiplier + "&3x level multiplier)", profile);
            sendMessage("&3+" + sAdd + " (&b" + rankMultiplier + "&3x rank multiplier) (&b" + lvlMultiplier + "&3x level multiplier)", profile);
        }
        profile.set("network.xp", add);
        profile.checkPrestige();
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

    default ItemStack getHead(Profile profile) {
        ItemStack item = new ItemStack(Material.SKULL_ITEM);
        SkullMeta skullMeta = (SkullMeta) item.getItemMeta();
        skullMeta.setOwner(profile.getName());
        item.setItemMeta(skullMeta);
        return item;
    }

    void levelUp(Profile profile);

    default void giveAchievementPoints(int achievementPoints, boolean message, Profile profile){
        if (message) {
            sendMessage("&9" + achievementPoints, profile);
        }
        profile.set("network.achievementPoints", (int) profile.get("network.achievementPoints") + achievementPoints);

        if (achievementPoints >= 1000) {
            profile.completeAchievement("achievements_I");
        }

        if (achievementPoints >= 10000) {
            profile.completeAchievement("achievements_II");
        }

        if (achievementPoints >= 100000) {
            profile.completeAchievement("achievements_III");
        }
    }
}
