package dev.dankom.core.menu.menus;

import dev.dankom.core.achievment.Achievement;
import dev.dankom.core.achievment.Achievements;
import dev.dankom.core.menu.Menu;
import dev.dankom.core.menu.MenuManager;
import dev.dankom.core.prestige.PrestigeIcon;
import dev.dankom.core.prestige.PrestigeIcons;
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

public class AchievementsMenu extends Menu {
    public AchievementsMenu() {
        super("&4Achievement Menu", false, 54);
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

        addAchievements(profile);

        return inventory;
    }

    @Override
    public void openInv(Profile profile) {
        createInv(profile);
        super.openInv(profile);
    }

    public void addAchievements(Profile profile) {
        for (int i = 0; i < inventory.getSize(); i++) {
            try {
                if (Achievements.getAchievements().get(i) == null) {
                    break;
                }
                Achievement a = Achievements.getAchievements().get(i);
                ItemHelper item = new ItemHelper(a.getIcon(), 1);
                item.setDisplayName(ChatColor.translateAlternateColorCodes('&', (a.isUnlocked(profile) ? "&a" : "&c") + a.getName()));
                List<String> lore = ListHelper.toList(a.getRewardLore());
                lore.add(0, "&7Rewards:");
                item.setLore(lore);
                inventory.setItem(i, item);
            } catch (IndexOutOfBoundsException e) {
                break;
            }
        }
    }

    @Override
    public void onClick(Profile player, int slot) {
        try {
            if (slot == 49) {
                player.player.closeInventory();
            } else if (slot == 45) {
                MenuManager.profileMenu.openInv(player);
            }
        } catch (IndexOutOfBoundsException e) {
            return;
        }
    }
}
