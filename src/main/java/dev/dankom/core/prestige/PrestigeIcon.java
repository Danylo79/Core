package dev.dankom.core.prestige;

import dev.dankom.core.profile.Profile;

public class PrestigeIcon {
    private final String prestigeIcon;
    private final String name;

    public PrestigeIcon(String prestigeIcon, String name) {
        this.prestigeIcon = prestigeIcon;
        this.name = name;
    }

    public String getPrestigeIcon() {
        return prestigeIcon;
    }

    public String getName() {
        return name;
    }

    public boolean isUnlocked(Profile profile) {
        return false;
    }

    public String getNeededToUnlock() {
        return "Not set!";
    }
}
