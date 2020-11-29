package dev.dankom.core.user;

import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import dev.dankom.logger.LogLevel;
import dev.dankom.logger.Logger;
import dev.dankom.util.Validation;
import dev.dankom.util.coreHelpers.CorePlayer;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UserManager {
    private ProtocolManager protocolManager;
    private static List<Player> users = new ArrayList<>();
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

    public Player getPlayer(UUID uuid) {
        for (Player cp : getPlayers()) {
            if (cp.getUniqueId().equals(uuid)) {
                return cp;
            } else {
                continue;
            }
        }
        Logger.log(LogLevel.ERROR, "Player instance not found in the UserManager class.");
        return null;
    }

    public Player getCorePlayer(UUID uuid) {
        for (Player cp : getPlayers()) {
            if (cp.getUniqueId().equals(uuid)) {
                return CorePlayer.toCorePlayer(cp);
            } else {
                continue;
            }
        }
        Logger.log(LogLevel.ERROR, "Player instance not found in the UserManager class.");
        return null;
    }

    public List<Player> getPlayers() {
        List<Player> out = new ArrayList<>();
        for (Player p : users) {
            out.add(p);
        }
        return out;
    }

    public void addPlayer(Player player) {
        Validation.notNull("Cannot add null player to users!", player);
        if (!contains(player) && player != null) {
            users.add(player);
        }
    }

    public void updatePlayer(UUID uuid, Player player) {
        users.remove(getPlayer(uuid));
        addPlayer(player);
    }

    public boolean contains(Player player) {
        return users.contains(player);
    }
}
