package dev.dankom.core.party;

import dev.dankom.Start;
import dev.dankom.core.file.yml.ConfigFile;
import dev.dankom.core.profile.Profile;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PartyManager {

    public Party getParty(String uuid) {
        if (database().get("parties." + uuid) != null) {
            List<Profile> players = new ArrayList<>();
            for (String u : database().getStringList("parties." + uuid + ".players")) {
                players.add(new Profile(UUID.fromString(u)));
            }
            Party party = new Party(
                    new Profile((String) get(uuid, "owner")),
                    UUID.fromString(uuid),
                    players
            );
            return party;
        } else {
            return null;
        }
    }

    public void addPlayer(String uuid, Profile profile) {
        database().getStringList("parties." + uuid + ".players").add(profile.player.getUniqueId().toString());
        save();
    }

    public void removePlayer(String uuid, Profile profile) {
        database().getStringList("parties." + uuid + ".players").remove(profile.player.getUniqueId().toString());
        save();
    }

    public Object get(String uuid, String key) {
        return database().get("parties." + uuid + "." + key);
    }

    public void set(String uuid, String key, Object value) {
        database().set("parties." + uuid + "." + key, value);
        save();
    }

    public void save() {
        database().saveConfig();
        database().reloadConfig();
    }

    public ConfigFile database() {
        return Start.getInstance().fileManager.partyFile;
    }
}
