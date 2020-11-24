package dev.dankom.core.party;

import dev.dankom.core.profile.Profile;

import java.util.List;
import java.util.UUID;

public class Party {
    private Profile owner;
    private final UUID stringId;
    private final List<Profile> players;

    public Party(Profile owner, UUID stringId, List<Profile> players) {
        this.owner = owner;
        this.stringId = stringId;
        this.players = players;
    }

    public UUID getStringId() {
        return stringId;
    }

    public List<Profile> getPlayers() {
        return players;
    }
}
