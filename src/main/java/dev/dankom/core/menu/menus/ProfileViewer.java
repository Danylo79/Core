package dev.dankom.core.menu.menus;

import dev.dankom.core.menu.Menu;
import dev.dankom.core.menu.MenuManager;
import dev.dankom.core.profile.Profile;
import dev.dankom.core.user.UserManager;
import dev.dankom.util.ItemHelper;
import dev.dankom.util.coreHelpers.CorePlayer;
import me.arcaniax.hdb.api.HeadDatabaseAPI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class ProfileViewer extends Menu {

    private ItemHelper profileHead;

    public ProfileViewer() {
        super("&7Profile Viewer", false, 9);
    }

    public Inventory createInv(Profile viewed, Profile profile) {
        this.inventory = Bukkit.createInventory(null, amtOfSlots, ChatColor.translateAlternateColorCodes('&', title));

        for (int i = 0; i < inventory.getSize(); i++) {
            ItemHelper item = new ItemHelper(Material.STAINED_GLASS_PANE, 1, DyeColor.GRAY);
            ItemMeta meta = item.getMeta();
            meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&0 "));
            item.setItemMeta(meta);
            inventory.setItem(i, item);
        }

        getHeads(viewed);
        getHeads(viewed);

        ItemHelper item = new ItemHelper(Material.BARRIER, 1);
        ItemMeta meta = item.getMeta();
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&cExit"));
        item.setItemMeta(meta);
        inventory.setItem(8, item);

        inventory.setItem(4, profileHead);

        item = new ItemHelper(Material.BREWING_STAND_ITEM, 1);
        item.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&6Network Data:"));
        List<String> lore = new ArrayList<>();
        for (String s : profile.getPlayer().toStrings()) {
            lore.add(ChatColor.translateAlternateColorCodes('&', "&7" + s));
        }
        item.setLore(lore);
        inventory.setItem(5, item);

        return inventory;
    }

    public void getHeads(Profile profile) {
        ItemHelper item = new ItemHelper(new HeadDatabaseAPI().getItemHead("38386"));
        item.setDisplayName(profile.getFullName());
        item.setSkullOwner(profile.getName());
        item.setLore(
                "&7Rank: " + profile.getRank().getDisplay(),
                "&7Level:&6 " + (int) profile.get("network.level"),
                "&7Experience until next level: &6" + ((int) profile.get("network.level") < 10000 ? profile.xpToNextLevel() : "&aMaxed!"),
                "&7Achievement Points:&e " + (int) profile.get("network.achievementPoints"),
                "&7Guild:&b " + profile.getGuild().getName(),
                " ",
                "&7Online Status: " + (profile.player.isOnline() ? "&aOnline" : "&cOffline")
        );
        profileHead = item;
    }

    public void openInv(Profile viewed, Profile profile) {
        createInv(viewed, profile);
        super.openInv(profile);
    }

    @Override
    public void onClick(Profile player, int slot) {
        try {
            if (slot == 8) {
                player.player.closeInventory();
            }
        } catch (IndexOutOfBoundsException e) {
            return;
        }
    }
}
