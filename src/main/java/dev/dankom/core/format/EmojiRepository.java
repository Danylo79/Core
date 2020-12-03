package dev.dankom.core.format;

import dev.dankom.core.profile.Profile;

import java.util.ArrayList;
import java.util.List;

public class EmojiRepository {
    private static List<Emoji> emojis = new ArrayList<>();

    public static List<Emoji> getEmojis() {
        emojis.clear();
        //Rank Based
        emojis.add(new Emoji("gg", "&6GG", 5));
        emojis.add(new Emoji("<3", "&c❤", 5));
        emojis.add(new Emoji(":math:", "&a√&e(&aΠ+x&e)&a=&cL", 5));
        emojis.add(new Emoji(":owo:", "&dO&5w&dO", 5));
        emojis.add(new Emoji(":123:", "&a1&e2&c3", 5));
        emojis.add(new Emoji(":star:", "&e✯", 5));
        emojis.add(new Emoji(">", "&1&c⇨", 5));
        emojis.add(new Emoji("<", "&1&c⇦", 5));
        emojis.add(new Emoji("OOF", "&c&lOOF", 5));
        emojis.add(new Emoji("oof", "&c&lOOF", 5));
        //Achievement Based
        emojis.add(new Emoji(":omega:", "Ω") {
            @Override
            public boolean customUnlock(Profile profile) {
                return profile.isAchievementUnlocked("omega");
            }

            @Override
            public String getNeededToUnlock() {
                return "&aOmega Achievement";
            }
        });
        emojis.add(new Emoji(":bitcoin:", "฿") {
            @Override
            public boolean customUnlock(Profile profile) {
                return profile.isAchievementUnlocked("coin_master_III");
            }

            @Override
            public String getNeededToUnlock() {
                return "&aCoin Master III Achievement";
            }
        });
        return emojis;
    }
}
