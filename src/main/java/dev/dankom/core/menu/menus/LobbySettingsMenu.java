package dev.dankom.core.menu.menus;

import dev.dankom.core.menu.Menu;
import dev.dankom.core.menu.MenuManager;
import dev.dankom.core.profile.Profile;
import dev.dankom.util.ItemHelper;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.meta.ItemMeta;

public class LobbySettingsMenu extends Menu {
    public LobbySettingsMenu() {
        super("&aLobby Settings", false, 54);
    }

    @Override
    public Inventory createInv(Profile profile) {
        this.inventory = Bukkit.createInventory(null, amtOfSlots, ChatColor.translateAlternateColorCodes('&', title));

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

        item = new ItemHelper(Material.ARROW, 1);
        meta = item.getMeta();
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&cBack"));
        item.setItemMeta(meta);
        inventory.setItem(45, item);

        item = new ItemHelper(Material.FEATHER, 1);
        meta = item.getMeta();
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&aFly"));
        item.setItemMeta(meta);
        inventory.setItem(21, item);

        item = new ItemHelper(Material.STRING, 1);
        meta = item.getMeta();
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&aHide/Show Players"));
        item.setItemMeta(meta);
        inventory.setItem(23, item);

        return inventory;
    }

    @Override
    public void openInv(Profile profile) {
        createInv(profile);
        super.openInv(profile);
    }

    @Override
    public void onClick(Profile player, int slot) {
        try {
            if (slot == 49) {
                player.player.closeInventory();
            } else if (slot == 45) {
                MenuManager.profileMenu.openInv(player);
            } else if (slot == 21) {
                player.player.chat("/lobby fly");
            } else if (slot == 23) {
                player.player.chat("/lobby hide");
            }
        } catch (IndexOutOfBoundsException e) {
            return;
        }
    }
}
