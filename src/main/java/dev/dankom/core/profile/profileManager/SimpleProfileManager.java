package dev.dankom.core.profile.profileManager;

import dev.dankom.core.profile.Profile;
import dev.dankom.core.rank.Rank;

public class SimpleProfileManager implements IProfileManager {
    @Override
    public void refreshPlayer(Profile profile) {
        profile.update();
        showOtherPlayers(profile);
        profile.player.setFlying(false);
    }

    @Override
    public void refreshLobbyPlayer(Profile profile) {
        profile.update();
        if ((boolean) profile.get("lobby.hidePlayers")) {
            hideOtherPlayers(profile);
        }
        if ((boolean) profile.get("lobby.fly")) {
            profile.player.setFlying(true);
        }
    }

    @Override
    public void setNick(String nick, Profile profile) {
        profile.set("nick.name", nick);
        profile.update();
    }

    @Override
    public void setRank(Rank rank, Profile profile) {
        profile.set("rank", rank.getId());
        sentActionbar("&aYour rank has been set to " + rank.getDisplay() + "&a!", profile);
        profile.update();
    }
}
