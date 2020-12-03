package dev.dankom.core.menu.menus;

import dev.dankom.core.menu.Menu;
import dev.dankom.core.menu.MenuManager;
import dev.dankom.core.prestige.PrestigeIcons;
import dev.dankom.core.profile.Profile;
import dev.dankom.util.ItemHelper;
import me.arcaniax.hdb.api.HeadDatabaseAPI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ProfileMenu extends Menu {
    private ItemStack profileHead;
    private ItemStack friendsHead;
    private ItemStack partyHead;
    private ItemStack guildHead;
    private ItemStack recentHead;

    public ProfileMenu() {
        super("&aProfile Menu", false, 54);
    }

    @Override
    public Inventory createInv(Profile profile) {
        this.inventory = Bukkit.createInventory(null, amtOfSlots, ChatColor.translateAlternateColorCodes('&', title));
        getHeads(profile);
        getHeads(profile);

        for (int i = 0; i < inventory.getSize(); i++) {
            ItemHelper item = new ItemHelper(Material.STAINED_GLASS_PANE, 1, DyeColor.GRAY);
            ItemMeta meta = item.getMeta();
            meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&0 "));
            item.setItemMeta(meta);
            inventory.setItem(i, item);
        }

        ItemHelper item = new ItemHelper(Material.BARRIER, 1);
        ItemMeta meta = item.getMeta();
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&cExit"));
        item.setItemMeta(meta);
        inventory.setItem(49, item);

        inventory.setItem(4, profileHead);

        item = new ItemHelper(Material.STAINED_GLASS_PANE, 1, DyeColor.ORANGE);
        meta = item.getMeta();
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&0 "));
        item.setItemMeta(meta);
        inventory.setItem(9, item);
        inventory.setItem(10, item);
        inventory.setItem(11, item);
        inventory.setItem(12, item);
        inventory.setItem(13, item);
        inventory.setItem(14, item);
        inventory.setItem(15, item);
        inventory.setItem(16, item);
        inventory.setItem(17, item);

        item = new ItemHelper(Material.FLOWER_POT_ITEM, 1);
        item.setDisplayName("&aLevel Icons (" + profile.get("network.prestigeIcon") + ")");
        item.setLore("&7Show everyone your", "&7achievements by", "&7having a custom icon", "&7next to your level!");
        inventory.setItem(20, item);

        item = new ItemHelper(Material.DIAMOND, 1);
        item.setDisplayName("&aAchievements");
        item.setLore("&7Check out your ", "&7progress on achievements!");
        inventory.setItem(21, item);

        item = new ItemHelper(profileHead);
        item.setDisplayName("&aPlayer Information");
        item.setLore(
                "&7Rank: " + profile.getRank().getDisplay(),
                "&7Level: &6" + (int) profile.get("network.level"),
                "&7Experience until next level: &6" + ((int) profile.get("network.level") < 10000 ? profile.xpToNextLevel() : "&aMaxed!"),
                "&7Achievement Points: &e" + profile.get("network.achievementPoints")
        );
        inventory.setItem(22, item);

        item = new ItemHelper(Material.EMERALD, 1);
        item.setDisplayName("&aCosmetics");
        item.setLore("&7Spice up your game with", "&7these unlockable cosmetics");
        inventory.setItem(23, item);

        item = new ItemHelper(Material.REDSTONE_COMPARATOR, 1);
        item.setDisplayName("&aLobby Settings");
        item.setLore("&7Change your lobby experience");
        inventory.setItem(24, item);

        item = new ItemHelper(Material.INK_SACK, 1, DyeColor.GRAY);
        item.setDisplayName("&aPlus Colors (" + profile.get("network.plus.color") + "+&a)");
        item.setLore("&7Change the color of your", "&7rank plus");
        inventory.setItem(29, item);


        return inventory;
    }

    @Override
    public void onClick(Profile player, int slot) {
        try {
            if (slot == 49) {
                player.player.closeInventory();
            } else if (slot == 20) {
                player.getMenuManager().prestigeMenu.openInv(player);
            } else if (slot == 21) {
                player.getMenuManager().achievementsMenu.openInv(player);
            } else if (slot == 23) {
                player.getMenuManager().cosmeticMenu.openInv(player);
            } else if (slot == 24) {
                player.getMenuManager().lobbySettingsMenu.openInv(player);
            } else if (slot == 29) {
                player.getMenuManager().plusColorsMenu.openInv(player);
            }
        } catch (IndexOutOfBoundsException e) {
            return;
        }
    }

    @Override
    public void onOpen(Profile player) {
        getHeads(player);
        getHeads(player);
    }

    public void getHeads(Profile profile) {
        ItemHelper item = new ItemHelper(new HeadDatabaseAPI().getItemHead("38386"));
        item.setDisplayName(profile.getFullName());
        item.setSkullOwner(profile.getName());
        item.setLore(
                "&7Level:&6 " + (int) profile.get("network.level"),
                "&7Achievement Points:&e " + (int) profile.get("network.achievementPoints"),
                "&7Guild:&b " + profile.getGuild().getName(),
                " ",
                "&7Online Status: " + (profile.player.isOnline() ? "&aOnline" : "&cOffline"));
        profileHead = item;

        item = new ItemHelper(new HeadDatabaseAPI().getItemHead("18190"));
        item.setDisplayName("&aGuild");
        item.setLore("&7Form a guild with other", "&7players to conquer", "&7game modes and work towards", "&7common rewards.");
        guildHead = item;

        item = new ItemHelper(new HeadDatabaseAPI().getItemHead("18191"));
        item.setDisplayName("&aFriends");
        item.setLore("&7View your friends", "&7profiles, and interact with", "&7your online friends!");
        friendsHead = item;

        item = new ItemHelper(new HeadDatabaseAPI().getItemHead("18181"));
        item.setDisplayName("&aParty");
        item.setLore("&7Create a party and join up", "&7with other players to play", "&7games together!");
        partyHead = item;

        item = new ItemHelper(new HeadDatabaseAPI().getItemHead("18179"));
        item.setDisplayName("&aRecent Players");
        item.setLore("&7View players you have", "&7played games with.");
        recentHead = item;
    }
}
