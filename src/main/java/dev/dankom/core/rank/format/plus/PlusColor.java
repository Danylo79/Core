package dev.dankom.core.rank.format.plus;

import dev.dankom.core.achievment.Achievement;
import dev.dankom.core.profile.Profile;
import dev.dankom.util.ItemHelper;
import org.bukkit.Color;
import org.bukkit.DyeColor;
import org.bukkit.Material;

public class PlusColor {
    private final String color;
    private final DyeColor dyeColor;
    private int level;
    private Achievement achievement;

    public PlusColor(String color, DyeColor dyeColor) {
        this.color = color;
        this.dyeColor = dyeColor;
    }

    public PlusColor(String color, Color dyeColor) {
        this.color = color;
        this.dyeColor = DyeColor.getByColor(dyeColor);
    }

    public PlusColor(String color) {
        this.color = color;
        this.dyeColor = DyeColor.valueOf(color);
    }

    public PlusColor(String color, DyeColor dyeColor, Achievement achievement) {
        this.achievement = achievement;
        this.color = color;
        this.dyeColor = dyeColor;
    }

    public PlusColor(String color, DyeColor dyeColor, int level) {
        this.color = color;
        this.dyeColor = dyeColor;
        this.level = level;
    }

    public boolean isUnlocked(Profile profile) {
        if (achievement != null) {
            return achievement.isUnlocked(profile);
        } else if (level != -1) {
            return profile.getLevel() >= level;
        }
        return false;
    }

    public boolean showInGUI() {
        return true;
    }

    public String getUnlockString() {
        if (achievement != null) {
            return "&7" + achievement.getName() + " achievement!";
        } else if (level != -1) {
            return "&7" + level + "+";
        }
        return "Not Set!";
    }

    public String getColor() {
        return color;
    }

    public DyeColor getDyeColor() {
        return dyeColor;
    }

    public ItemHelper getIcon() {
        return new ItemHelper(Material.STAINED_GLASS_PANE, 1, getDyeColor());
    }
}
