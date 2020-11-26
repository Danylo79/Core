package dev.dankom.core.inbox;

import dev.dankom.core.file.yml.ConfigFile;
import dev.dankom.core.profile.Profile;

public class InboxEntryType {

    private String name;

    public InboxEntryType(String name) {
        this.name = name;
    }

    public String getName() {
        return name.toUpperCase();
    }

    public void send(Profile profile, Profile sender, ConfigFile database) {
        profile.set("inbox." + sender.player.getUniqueId().toString() + ".type", getName());
        profile.set("inbox." + sender.player.getUniqueId().toString() + ".sender", sender.player.getUniqueId().toString());
    }
}
