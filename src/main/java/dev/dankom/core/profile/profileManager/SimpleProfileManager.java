package dev.dankom.core.profile.profileManager;

import dev.dankom.core.format.ChatFormat;
import dev.dankom.core.profile.Profile;
import dev.dankom.core.rank.Rank;
import org.bukkit.ChatColor;

public class SimpleProfileManager implements IProfileManager {
    @Override
    public void refreshPlayer(Profile profile) {
        profile.update();
        try {
            showOtherPlayers(profile);
            profile.player.setAllowFlight(false);
        } catch (IllegalStateException e) {
            return;
        }
    }

    @Override
    public void refreshLobbyPlayer(Profile profile) {
        profile.update();
        if ((boolean) profile.get("lobby.hidePlayers")) {
            hideOtherPlayers(profile);
        } else {
            showOtherPlayers(profile);
        }
        profile.player.setAllowFlight((boolean) profile.get("lobby.fly"));
    }

    @Override
    public void setNick(String nick, Profile profile) {
        profile.set("nick.name", nick);
        profile.update();
    }

    @Override
    public void setRank(Rank rank, Profile profile) {
        profile.set("rank", rank.getId());
        sendActionbar("&aYour rank has been set to " + rank.getDisplay(profile) + "&a!", profile);
        profile.update();
    }

    @Override
    public String getChatFormat(String msg, Profile profile) {
        return ChatColor.translateAlternateColorCodes('&', new ChatFormat(msg, profile).format());
    }

    @Override
    public void levelUp(Profile profile) {
        sendActionbar("&6Leveled up!", profile);
        sendMessage("&6You leveled up to level " + (int) profile.get("network.level") + "!", profile);
    }
}
