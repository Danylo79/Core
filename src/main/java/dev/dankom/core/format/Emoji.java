package dev.dankom.core.format;

import dev.dankom.core.profile.Profile;
import dev.dankom.core.rank.Rank;
import org.bukkit.entity.Player;

public class Emoji {
    private final String text;
    private final String replacement;
    private final int rank;

    public Emoji(String text, String replacement, int rank) {
        this.text = text;
        this.replacement = replacement;
        this.rank = rank;
    }

    public Emoji(String text, String replacement) {
        this.text = text;
        this.replacement = replacement;
        this.rank = -1;
    }

    public String format(Player player, String text) {
        Profile profile = new Profile(player);
        if (isUnlocked(new Profile(player))) {
            return text.replaceAll(getText(), getReplacement());
        } else {
            return text;
        }
    }

    public boolean isUnlocked(Profile profile) {
        if (rank != -1) {
            return profile.getRank().getId() >= getRank();
        } else {
            return customUnlock(profile);
        }
    }

    public boolean customUnlock(Profile profile) {
        return false;
    }

    public String getText() {
        return text;
    }

    public String getReplacement() {
        return replacement;
    }

    public int getRank() {
        return rank;
    }

    public String getNeededToUnlock() {
        return "&a" + Rank.get(getRank()).getDisplay() + "rank!";
    }
}
