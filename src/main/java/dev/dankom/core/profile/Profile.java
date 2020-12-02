package dev.dankom.core.profile;

import com.nametagedit.plugin.NametagEdit;
import dev.dankom.Start;
import dev.dankom.core.achievment.Achievement;
import dev.dankom.core.achievment.Achievements;
import dev.dankom.core.cosmetics.CosmeticType;
import dev.dankom.core.file.yml.ConfigFile;
import dev.dankom.core.guild.Guild;
import dev.dankom.core.guild.GuildManager;
import dev.dankom.core.inbox.Inbox;
import dev.dankom.core.profile.profileManager.IProfileManager;
import dev.dankom.core.profile.profileManager.SimpleProfileManager;
import dev.dankom.core.rank.Rank;
import dev.dankom.game.core.GameManager;
import dev.dankom.game.core.interfaces.IGame;
import dev.dankom.util.ColorUtil;
import dev.dankom.util.coreHelpers.core.CorePlayer;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
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
        addData("coins", 0);
        addData("guild", "");
        addData("friends", new ArrayList<String>());
        addData("inbox", "");
        //Network Data
        addData("network.level", 1);
        addData("network.xp", 0);
        addData("network.achievementPoints", 0);
        addData("network.prestigeIcon", "");
        addData("network.lobby", false);
        addData("network.game.id", "");
        addData("network.plus.color", "&7");
        //Lobby
        addData("lobby.hidePlayers", false);
        addData("lobby.fly", false);
        //Nick
        addData("nick.nicked", false);
        addData("nick.name", "");
        addData("nick.rank", 0);
        //Achievements
        for (Achievement a : Achievements.getAchievements()) {
            addData("achievement." + a.getDatabaseName(), false);
        }
        //Cosmetics
        for (CosmeticType c : CosmeticType.values()) {
            addData("cosmetic." + c.getDatabaseName(), "");
        }
    }

    public void update() {
        player.setPlayerListName(getFullName());
        NametagEdit.getApi().setNametag(player, getRank().getColor(), "");
    }

    public Rank getRank() {
        if ((boolean) get("nick.nicked")) {
            return Rank.get((Integer) get("nick.nicked"));
        } else {
            return Rank.get((Integer) get("rank"));
        }
    }

    private void addData(String s, Object s1) {
        if (get(s) == null || (s1 instanceof Integer && get(s) instanceof Integer && (int) get(s) == 0) || (s1 instanceof Double && get(s) instanceof Double && (double) get(s) == 0)) {
            set(s, s1);
        }
    }

    public Object get(String key) {
        return database().get("profiles." + player.getUniqueId() + "." + key);
    }

    public void set(String key, Object value) {
        database().set("profiles." + player.getUniqueId() + "." + key, value);
        save();
        getProfileManager().refreshPlayer(this);
    }

    public void save() {
        database().saveConfig();
        database().reloadConfig();
    }

    public int getLevel() {
        return (int) get("network.level");
    }

    public ConfigFile database() {
        return Start.getInstance().getFileManager().databaseFile;
    }

    public String getFullName() {
        return ChatColor.translateAlternateColorCodes('&', getPrefix() + getName() + getSuffix());
    }

    public String getPrefix() {
        return ChatColor.translateAlternateColorCodes('&', getRank().getColor() + getRank().getDisplay(this) + (getRank().getDisplay(this).equals("") ? "" : " "));
    }

    public String getSuffix() {
        return ChatColor.translateAlternateColorCodes('&', getGuildTag());
    }

    public String getGuildTag() {
        if (getGuild() != null && !getGuild().getColoredTag().equals("") && !(boolean) get("nick.nicked")) {
            return " " + getGuild().getColoredTag();
        }
        return "";
    }

    public String getName() {
        save();
        if ((boolean) get("nick.nicked")) {
            return (String) get("nick.name");
        } else {
            return (String) get("name");
        }
    }

    public String getLevelTag() {
        int lvl = (int) get("network.level");
        return ChatColor.translateAlternateColorCodes('&', formatLevelTag(lvl));
    }

    public String getLevelColor() {
        int lvl = (int) get("network.level");
        if (lvl > 10 && lvl < 20) {
            return "&d";
        } else if (lvl >= 20 && lvl <= 50) {
            return "&2";
        } else if (lvl >= 50 && lvl <= 100) {
            return "&c";
        } else if (lvl >= 100 && lvl <= 200) {
            return "&b";
        } else if (lvl >= 200 && lvl <= 300) {
            return "&5";
        } else if (lvl >= 300 && lvl <= 400) {
            return "&e";
        } else if (lvl >= 400 && lvl <= 500) {
            return "&6";
        } else if (lvl >= 500 && lvl < 1000) {
            return "&3";
        }
        return "&7";
    }

    public String formatLevelTag(int lvl) {
        String base = lvl + (String) get("network.prestigeIcon");
        if (lvl < 1000) {
            return getLevelColor() + "[" + base + "]";
        } else if (lvl >= 1000 && lvl < 10000) {
            String out = "[" + lvl + "]";
            if (lvl >= 1000 && lvl <= 1100) {
                out = "&e" + out.charAt(0) +
                        "&e" + out.charAt(0) +
                        "&6" + out.charAt(1) +
                        "&6" + out.charAt(1) +
                        "&c" + out.charAt(1) +
                        "&c" + out.charAt(1) +
                        "&c" + out.charAt(1);
            } else if (lvl >= 1100 && lvl <= 1200) {
                out = "&5" + out.charAt(0) +
                        "&d" + out.charAt(0) +
                        "&5" + out.charAt(1) +
                        "&d" + out.charAt(1) +
                        "&5" + out.charAt(1) +
                        "&d" + out.charAt(1) +
                        "&5" + out.charAt(1);
            } else {
                out = "&3" + out;
            }
            return out;
        } else if (lvl <= 10000) {
            return ColorUtil.toRainbow("[" + base + "]");
        }
        return "[" + lvl + "]";
    }

    public boolean checkPrestige() {
        int level = (int) get("network.level");
        if (level < 10000) {
            double xp;
            if (get("network.xp") instanceof Integer) {
                xp = ((Integer) get("network.xp")).doubleValue();
            } else {
                xp = (double) get("network.xp");
            }
            boolean reached = xp >= xpToNextLevel();

            if (reached) {
                set("network.level", level + 1);
                set("network.xp", xp - xpToNextLevel());
                getProfileManager().levelUp(this);
                if (xp > 0) {
                    checkPrestige();
                }
            }
            return reached;
        }
        return false;
    }

    public double xpToNextLevel() {
        int level = (int) get("network.level");
        int neededXp = level * (1000 / 5);
        return neededXp;
    }

    public void completeAchievement(String databaseName) {
        Achievement achievement = Achievements.getAchievement(databaseName);
        if (isAchievementUnlocked(databaseName)) {
            return;
        }
        set("achievement." + databaseName, true);
        getProfileManager().giveAchievementPoints(achievement.getAchievementPoints(), true, this);
        getProfileManager().sendMessage("&a&kW&r&a>> Achievement Unlocked: &6" + achievement.getName() + " &a<<&kW", this);
    }

    public boolean isAchievementUnlocked(String databaseName) {
        return Achievements.getAchievement(databaseName).isUnlocked(this);
    }

    public Guild getGuild() {
        return GuildManager.getInstance().get((String) get("guild"));
    }

    public IProfileManager getProfileManager() {
        return new SimpleProfileManager();
    }

    public Inbox getInbox() {
        return new Inbox(this);
    }

    public List<Profile> getFriends() {
        List<Profile> friends = new ArrayList<>();
        for (String s : database().getStringList("profiles." + player.getUniqueId().toString() + ".friends")) {
            friends.add(new Profile(UUID.fromString(s)));
        }
        return friends;
    }

    public CorePlayer getPlayer() {
        return CorePlayer.toCorePlayer(player);
    }

    public IGame getGame() {
        return GameManager.getInstance().getGameRepository().getGame(UUID.fromString((String) get("network.game.id")));
    }

    public void resetNick() {
        set("nick.nicked", false);
        set("nick.name", "");
        set("nick.id", "");
    }

    public void disableNick() {
        set("nick.nicked", false);
    }

    public static class Nick {
        private String name;
        private Rank rank;

        public Nick(String name, Rank rank) {
            this.name = name;
            this.rank = rank;
        }

        public Nick() {
            this.name = "Not Set";
            this.rank = Rank.NONE;
        }

        public void set(Profile profile) {
            profile.set("nick.nicked", true);
            profile.set("nick.name", getName());
            profile.set("nick.rank", getRank().getId());
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Rank getRank() {
            return rank;
        }

        public void setRank(Rank rank) {
            this.rank = rank;
        }
    }
}
