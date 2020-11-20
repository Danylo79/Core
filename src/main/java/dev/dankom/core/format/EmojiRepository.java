package dev.dankom.core.format;

import java.util.ArrayList;
import java.util.List;

public class EmojiRepository {
    private static List<Emoji> emojis = new ArrayList<>();

    public static List<Emoji> getEmojis() {
        emojis.clear();
        emojis.add(new Emoji("gg", "&6GG", 5));
        emojis.add(new Emoji("<3", "&c❤", 5));
        emojis.add(new Emoji(":math:", "&a√&e(&aΠ+x&e)&a=&cL", 5));
        emojis.add(new Emoji(":owo:", "&dO&5w&dO", 5));
        emojis.add(new Emoji(":123:", "&a1&e2&c3", 5));
        emojis.add(new Emoji(":star:", "&e✯", 5));
        return emojis;
    }
}
