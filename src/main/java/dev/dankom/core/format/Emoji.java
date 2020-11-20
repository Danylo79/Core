package dev.dankom.core.format;

import dev.dankom.core.profile.Profile;
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

    public String format(Player player, String text) {
        Profile profile = new Profile(player);
        if (profile.getRank().getId() >= getRank()) {
            return text.replace(text, replacement);
        } else {
            return text;
        }
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
}
