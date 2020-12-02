package dev.dankom.core.menu.menus.cosmetic;

import dev.dankom.core.achievment.Achievement;
import dev.dankom.core.achievment.Achievements;
import dev.dankom.core.menu.Menu;
import dev.dankom.core.menu.MenuManager;
import dev.dankom.core.profile.Profile;
import dev.dankom.util.ItemHelper;
import dev.dankom.util.ListHelper;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class CosmeticMenu extends Menu {
    public CosmeticMenu() {
        super("&6Cosmetics", false, 54);
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
        item.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&cExit"));
        inventory.setItem(49, item);

        item = new ItemHelper(Material.ARROW, 1);
        item.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&cBack"));
        inventory.setItem(45, item);

        item = new ItemHelper(Material.SIGN, 1);
        item.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&aKill Messages"));
        inventory.setItem(10, item);

        item = new ItemHelper(Material.ARROW, 1);
        item.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&aProjectile Trails"));
        inventory.setItem(12, item);

        item = new ItemHelper(Material.DIAMOND_SWORD, 1);
        item.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&aKill Effects"));
        inventory.setItem(14, item);

        item = new ItemHelper(Material.STRING, 1);
        item.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&aClick Effects"));
        inventory.setItem(16, item);

        return inventory;
    }

    @Override
    public void onClick(Profile player, int slot) {
        try {
            if (slot == 49) {
                player.player.closeInventory();
            } else if (slot == 45) {
                new MenuManager().profileMenu.openInv(player);
            } else if (slot == 10) {
                new MenuManager().killMessagesMenu.openInv(player);
            } else if (slot == 12) {
                new MenuManager().projectileTrailMenu.openInv(player);
            } else if (slot == 14) {
                new MenuManager().killEffectMenu.openInv(player);
            } else if (slot == 16) {
                new MenuManager().clickEffectMenu.openInv(player);
            }
        } catch (IndexOutOfBoundsException e) {
            return;
        }
    }
}
