package dev.dankom.core.profile;

import dev.dankom.core.file.FileManager;
import dev.dankom.core.file.yml.ConfigFile;
import dev.dankom.core.profile.profileManager.IProfileManager;
import dev.dankom.core.profile.profileManager.SimpleProfileManager;
import dev.dankom.core.rank.Rank;
import dev.dankom.logger.LogLevel;
import dev.dankom.logger.Logger;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.UUID;

public class Profile {
    public Player player;

    public Profile(Player player) {
        this.player = player;
    }

    public Profile(String player) {
        this.player = Bukkit.getPlayer(player);
    }

    public Profile(UUID player) {
        this.player = Bukkit.getPlayer(player);
    }

    public void addPlayerData() {
        //Basic
        addData("uuid", player.getUniqueId().toString());
        addData("name", player.getName());
        addData("rank", 0);
        addData("coins", 0.0);
        //Network Data
        addData("network.level", 0);
        addData("network.xp", 0);
        addData("network.lobby", false);
        //Lobby
        addData("lobby.hidePlayers", false);
        addData("lobby.fly", false);
        //Nick
        addData("nick.nicked", false);
        addData("nick.name", "");
        addData("nick.rank", 0);
    }

    public void update() {
        player.setDisplayName(getFullName());
        player.setPlayerListName(getFullName());
    }

    public Rank getRank() {
        if (database().getBoolean("nick.nicked")) {
            return Rank.get(database().getInt("nick.rank"));
        } else {
            return Rank.get(database().getInt("rank"));
        }
    }

    private void addData(String s, Object s1) {
        if (database().get("profiles." + player.getUniqueId() + s) == null) {
            database().set("profiles." + player.getUniqueId() + "." + s, s1);
        }
    }

    public Object get(String key) {
        return database().get("profiles." + player.getUniqueId() + "." + key);
    }

    public void set(String key, Object value) {
        Logger.log(LogLevel.INFO, "Key:" + key + " Value: " + value);
        database().set("profiles." + player.getUniqueId() + "." + key, value);
        save();
    }

    public void save() {
        database().saveConfig();
        database().reloadConfig();
    }

    public ConfigFile database() {
        return FileManager.databaseFile;
    }

    public String getFullName() {
        return ChatColor.translateAlternateColorCodes('&', getRank().getColor() + getRank().getName() + (getRank().getName().equals("") ? "" : " ") + getName());
    }

    public String getName() {
        if (database().getBoolean("nick.nicked")) {
            return (String) get("nick.name");
        } else {
            return (String) get("name");
        }
    }

    public IProfileManager getProfileManager() {
        return new SimpleProfileManager();
    }
}
