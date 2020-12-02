package dev.dankom.core.rank.plus;

import dev.dankom.core.profile.Profile;

public class PlusColor {
    private final String color;

    public PlusColor(String color) {
        this.color = color;
    }

    public boolean isUnlocked(Profile profile) {
        return false;
    }

    public String getColor() {
        return color;
    }
}
