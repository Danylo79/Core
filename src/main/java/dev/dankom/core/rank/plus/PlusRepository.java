package dev.dankom.core.rank.plus;

import dev.dankom.core.profile.Profile;

import java.util.ArrayList;
import java.util.List;

public class PlusRepository {
    private static List<PlusColor> plusColors = new ArrayList<>();

    public static List<PlusColor> getPlusColors() {
        plusColors.clear();
        //Add
        plusColors.add(new PlusColor("&7") {
            @Override
            public boolean isUnlocked(Profile profile) {
                return true;
            }
        });
        //Level Based
        plusColors.add(new PlusColor("&c") {
            @Override
            public boolean isUnlocked(Profile profile) {
                return profile.getLevel() > 50;
            }
        });
        plusColors.add(new PlusColor("&a") {
            @Override
            public boolean isUnlocked(Profile profile) {
                return profile.getLevel() > 100;
            }
        });
        plusColors.add(new PlusColor("&6") {
            @Override
            public boolean isUnlocked(Profile profile) {
                return profile.getLevel() > 150;
            }
        });
        plusColors.add(new PlusColor("&b") {
            @Override
            public boolean isUnlocked(Profile profile) {
                return profile.getLevel() > 200;
            }
        });
        //Achievement Based
        plusColors.add(new PlusColor("&3") {
            @Override
            public boolean isUnlocked(Profile profile) {
                return profile.isAchievementUnlocked("lunar_join");
            }
        });
        //
        return plusColors;
    }
}
