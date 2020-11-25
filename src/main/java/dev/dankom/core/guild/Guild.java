package dev.dankom.core.guild;

import dev.dankom.core.profile.Profile;
import dev.dankom.util.ColorUtil;
import dev.dankom.util.linkedlist.Node;
import net.md_5.bungee.api.ChatColor;

import java.util.ArrayList;
import java.util.List;

public class Guild {
    private final Profile owner;
    private final int playerCount;
    private final String name;
    private final String tag;
    private final int lvl;
    private final int gexp;

    public Guild(Profile owner, int playerCount, String name, String tag, int lvl, int gexp) {
        this.owner = owner;
        this.playerCount = playerCount;
        this.name = name.toUpperCase();
        this.tag = tag;
        this.lvl = lvl;
        this.gexp = gexp;
    }

    public String getName() {
        return name;
    }

    public String getTag() {
        if (!tag.equals("")) {
            return "[" + tag + "]";
        } else {
            return "";
        }
    }

    public String getColoredTag() {
        if (getLvl() < 1000) {
            return ChatColor.translateAlternateColorCodes('&', getTagColor() + getTag());
        } else {
            return ChatColor.translateAlternateColorCodes('&', ColorUtil.toRainbow(getTag()));
        }
    }

    public int getLvl() {
        return lvl;
    }

    public int getGexp() {
        return gexp;
    }

    public String getTagColor() {
        if (getLvl() > 10 && getLvl() < 20) {
            return "&d";
        } else if (getLvl() >= 20 && getLvl() <= 50) {
            return "&2";
        } else if (getLvl() >= 50 && getLvl() <= 100) {
            return "&c";
        } else if (getLvl() >= 100 && getLvl() <= 200) {
            return "&d";
        } else if (getLvl() >= 200 && getLvl() <= 300) {
            return "&5";
        } else if (getLvl() >= 300 && getLvl() <= 400) {
            return "&e";
        } else if (getLvl() >= 400 && getLvl() <= 500) {
            return "&6";
        } else if (getLvl() >= 500 && getLvl() < 1000) {
            return "&3";
        }
        return "&7";
    }

    public Profile getOwner() {
        return owner;
    }

    public int getPlayerCount() {
        return playerCount;
    }

    public void refresh() {
        GuildManager.getInstance().addGuildData(getOwner(), getPlayerCount(), getName(), getTag(), getLvl(), getGexp());
    }

    public boolean checkPrestige() {
        int level = (int) getLvl();
        GuildManager guildManager = GuildManager.getInstance();
        if (level < 10000) {
            double xp = (double) getGexp();
            int neededXp = level * (1000 / 5);
            boolean reached = xp >= neededXp;

            if (reached) {
                guildManager.set(getName(), "level", level + 1);
                guildManager.set(getName(), "gexp", xp - neededXp);
            }
            guildManager.levelUp(this);
            if (reached && xp > 0) {
                checkPrestige();
            }
            return reached;
        }
        return false;
    }

    private List<String> getGuildPlayers() {
        return GuildManager.getInstance().database().getStringList(getName() + ".players");
    }

    public List<Profile> getPlayers() {
        List<Profile> profiles = new ArrayList<>();
        for (String s : getGuildPlayers()) {
            profiles.add(new Profile(s));
        }
        return profiles;
    }
}
