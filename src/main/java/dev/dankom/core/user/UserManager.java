package dev.dankom.core.user;

import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import dev.dankom.core.Core;
import dev.dankom.core.profile.Profile;
import dev.dankom.util.spigot.CorePlayer;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UserManager {
    private ProtocolManager protocolManager;
    private static List<Profile> users = new ArrayList<>();
    private static UserManager instance;

    public UserManager() {
        this.protocolManager = ProtocolLibrary.getProtocolManager();
        instance = this;
    }

    public static UserManager getInstance() {
        if (instance == null) {
            instance = new UserManager();
        }
        return instance;
    }

    public CorePlayer getPlayer(UUID uuid) {
        for (CorePlayer cp : getPlayers()) {
            if (cp.getUniqueId() == uuid) {
                return cp;
            } else {
                continue;
            }
        }
        return null;
    }

    public List<CorePlayer> getPlayers() {
        List<CorePlayer> out = new ArrayList<>();
        for (Profile p : users) {
            out.add(CorePlayer.toCorePlayer(p.player));
        }
        return out;
    }

    public void addPlayer(Player player) {
        users.add(new Profile(player));
    }
}
