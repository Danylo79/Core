package dev.dankom.core.achievment;

import dev.dankom.core.profile.Profile;
import org.bukkit.Material;

public class Achievement {
    private final String name;
    private final String databaseName;
    private final Material icon;
    private final int achievementPoints;
    private String unlockString;
    private final String[] rewardLore;

    public Achievement(String name, String databaseName, Material icon, int achievementPoints, String unlockString, String... rewardLore) {
        this.name = name;
        this.databaseName = databaseName;
        this.icon = icon;
        this.achievementPoints = achievementPoints;
        this.unlockString = unlockString;
        this.rewardLore = rewardLore;
    }

    public void unlock() {}

    public boolean isUnlocked(Profile profile) {
        return (boolean) profile.get("achievement." + getDatabaseName());
    }

    public String getName() {
        return name;
    }

    public Material getIcon() {
        return icon;
    }

    public String[] getRewardLore() {
        return rewardLore;
    }

    public int getAchievementPoints() {
        return achievementPoints;
    }

    public String getDatabaseName() {
        return databaseName;
    }

    public String getUnlockString() {
        return unlockString;
    }

    public void setUnlockString(String unlockString) {
        this.unlockString = unlockString;
    }
}
