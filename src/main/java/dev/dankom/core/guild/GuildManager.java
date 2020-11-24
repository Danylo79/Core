package dev.dankom.core.guild;

import dev.dankom.Start;
import dev.dankom.core.file.yml.ConfigFile;
import dev.dankom.core.profile.Profile;
import dev.dankom.core.profile.profileManager.IProfileManager;
import dev.dankom.util.ListHelper;

import java.util.ArrayList;
import java.util.List;

public class GuildManager {

    private static GuildManager instance;

    public static GuildManager getInstance() {
        if (instance == null) {
            instance = new GuildManager();
        }
        return instance;
    }

    public boolean createGuild(Profile owner, String name) {
        if (get(name) == null && owner.getGuild() == null) {
            addGuildData(owner, 1, name, "", 1, 0);
            owner.set("guild", name);
            return true;
        }
        return false;
    }

    public void addGuildData(Profile owner, int playerCount, String name, String tag, int lvl, int gexp) {
        addData(name, "name", name);
        addData(name, "tag", tag);
        addData(name, "owner", owner.getName());
        addData(name, "count", playerCount);
        addData(name, "level", lvl);
        addData(name, "gexp", gexp);

        addData(name, "players", ListHelper.toList(owner.player.getUniqueId().toString()));
    }

    public void addData(String name, String s, Object s1) {
        if (get(name, s) == null || (s1 instanceof Integer && (int) get(name, s) == 0)) {
            set(name, s, s1);
        }
    }

    public Guild get(String name) {
        if (get(name, "name") != null) {
            Guild guild = new Guild(
                    new Profile(
                            (String) get(name, "owner")
                    ),
                    (int) get(name, "count"),
                    (String) get(name, "name"),
                    (String) get(name, "tag"),
                    (int) get(name, "level"),
                    (int) get(name, "gexp")
            );
            return guild;
        } else {
            return null;
        }
    }

    public Object get(String guildName, String key) {
        return database().get("guilds." + guildName + "." + key);
    }

    public void set(String guildName, String key, Object value) {
        database().set("guilds." + guildName + "." + key, value);
        save();
    }

    public void save() {
        database().saveConfig();
        database().reloadConfig();
    }

    public ConfigFile database() {
        return Start.getInstance().getFileManager().guildFile;
    }

    public void giveGEXP(String guildName, int amt) {
        set(guildName, "gexp", (int) get(guildName, "gexp") + amt);
        get(guildName).checkPrestige();
    }

    public void addToGuild(Profile profile, String guildName) {
        database().getStringList("guilds." + guildName + ".players").add(profile.player.getUniqueId().toString());
        profile.set("guild", guildName);
        save();
    }

    public void levelUp(Guild guild) {

    }

    public void sendGuildNotification(Guild guild, List<String> msg, boolean actionbar) {
        List<Profile> players = getAllPlayers(guild.getName());
        for (Profile p : players) {
            IProfileManager profileManager = p.getProfileManager();
            for (String m : msg) {
                profileManager.sendMessage(m, p);
                if (actionbar) {
                    profileManager.sendActionbar(m, p);
                }
            }
        }
    }

    private List<Profile> getAllPlayers(String guildName) {
        List<String> strings = database().getStringList("guilds." + guildName + ".players");
        List<Profile> out = new ArrayList<>();
        for (String s : strings) {
            out.add(new Profile(s));
        }
        return out;
    }
}
