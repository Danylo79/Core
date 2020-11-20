package dev.dankom.core.format;

import dev.dankom.core.profile.Profile;

public class ChatFormat {
    private final String msg;
    private final Profile profile;

    public ChatFormat(String msg, Profile profile) {
        this.msg = msg;
        this.profile = profile;
    }

    public String format() {
        String message = profile.getFullName() + "&f: " + msg;
        for (Emoji e : EmojiRepository.getEmojis()) {
            message = e.format(getProfile().player, message);
        }
        return message;
    }

    public String getMsg() {
        return msg;
    }

    public Profile getProfile() {
        return profile;
    }
}
