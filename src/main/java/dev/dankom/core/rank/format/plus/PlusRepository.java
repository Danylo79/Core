package dev.dankom.core.rank.format.plus;

import dev.dankom.core.achievment.Achievements;
import dev.dankom.core.profile.Profile;
import org.bukkit.DyeColor;

import java.util.ArrayList;
import java.util.List;

public class PlusRepository {
    private static List<PlusColor> plusColors = new ArrayList<>();

    public static List<PlusColor> getPlusColors() {
        plusColors.clear();
        //Add
        plusColors.add(new PlusColor("&7", DyeColor.GRAY) {
            @Override
            public boolean isUnlocked(Profile profile) {
                return true;
            }
        });
        //Level Based
        plusColors.add(new PlusColor("&c", DyeColor.RED, 50));
        plusColors.add(new PlusColor("&a", DyeColor.LIME, 100));
        plusColors.add(new PlusColor("&6", DyeColor.YELLOW, 150));
        plusColors.add(new PlusColor("&b", DyeColor.LIGHT_BLUE, 200));
        //Achievement Based
        plusColors.add(new PlusColor("&3", DyeColor.CYAN, Achievements.getAchievement("lunar_join")));
        //
        return plusColors;
    }
}
