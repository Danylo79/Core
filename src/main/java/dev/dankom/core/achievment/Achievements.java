package dev.dankom.core.achievment;

import org.bukkit.Material;

import java.util.ArrayList;
import java.util.List;

public class Achievements {
    private static List<Achievement> achievements = new ArrayList<>();

    public static List<Achievement> getAchievements() {
        achievements.clear();
        achievements.add(new Achievement("Amateur Merchant", "coin_master_I", Material.GOLD_NUGGET, 1000, "&6Get 100 coins", "&3+1000 Achievement Points", "&aAmateur Merchant level icon"));
        achievements.add(new Achievement("Wealthy Merchant", "coin_master_II", Material.GOLD_NUGGET, 5000, "&6Get 10, 000 coins", "&3+5000 Achievement Points", "&aWealthy Merchant level icon"));
        achievements.add(new Achievement("Filthy Rich Merchant", "coin_master_III", Material.GOLD_NUGGET, 10000, "&6Get 100, 000 coins", "&3+10000 Achievement Points", "&aFilthy Rich Merchant level icon"));
        achievements.add(new Achievement("Omega", "omega", Material.DIAMOND, 25000, "&c??????????", "&3+25000 Achievement Points", "&aOmega level icon"));
        achievements.add(new Achievement("Achievements!?", "achievements_I", Material.REDSTONE, 1000, "&3Get 1000 achievement points", "&3+1000 Achievement Points"));
        achievements.add(new Achievement("Achievements!?!?", "achievements_II", Material.REDSTONE, 5000, "&3Get 10, 000 achievement points", "&3+5000 Achievement Points"));
        achievements.add(new Achievement("Achievements!?!?!?", "achievements_III", Material.REDSTONE, 10000, "&3Get 100, 000 achievement points", "&3+10000 Achievement Points"));
        achievements.add(new Achievement("Let the world hear your voice!", "chat", Material.SIGN, 100, "&eChat for the first time", "&3+100 Achievement Points"));
        achievements.add(new Achievement("On the Moon!", "lunar_join", Material.ENDER_STONE, 150, "&9Join the server using lunar client.", "&3+150 Achievement Points", "&aLunar level icon"));
        return achievements;
    }

    public static Achievement getAchievement(String databaseName) {
        for (Achievement a : getAchievements()) {
            if (a.getDatabaseName().equalsIgnoreCase(databaseName)) {
                return a;
            } else {
                continue;
            }
        }
        return null;
    }
}
