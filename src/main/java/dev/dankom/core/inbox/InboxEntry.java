package dev.dankom.core.inbox;

import dev.dankom.core.file.yml.ConfigFile;
import dev.dankom.core.profile.Profile;

public class InboxEntry {

    private Profile profile;
    private Profile sender;
    private InboxEntryType iet;

    public InboxEntry(Profile profile, Profile sender, InboxEntryType iet) {
        this.profile = profile;
        this.sender = sender;
        this.iet = iet;
    }

    public void send(ConfigFile database) {
        iet.send(profile, sender, database);
    }
}
