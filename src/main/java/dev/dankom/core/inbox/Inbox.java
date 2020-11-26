package dev.dankom.core.inbox;

import dev.dankom.core.file.yml.ConfigFile;
import dev.dankom.core.profile.Profile;

public class Inbox {

    public static InboxEntryType FRIEND_REQUEST = new InboxEntryType("friend_request");
    public static InboxEntryType GUILD_REQUEST = new InboxEntryType("guild_request");

    private Profile profile;

    public Inbox(Profile profile) {
        this.profile = profile;
    }

    public void addToInbox(InboxEntry ie) {
        ie.send(database());
    }

    public InboxEntry createEntry(InboxEntryType iet, Profile sender, Profile profile) {
        return new InboxEntry(profile, sender, iet);
    }

    public void removeEntry(Profile sender) {
        profile.set("inbox." + sender.player.getUniqueId().toString(), "Accepted");
    }

    public Object get(Profile sender, String key) {
        return profile.get("inbox." + sender.player.getUniqueId().toString() + "." + key);
    }

    public ConfigFile database() {
        return profile.database();
    }
}
