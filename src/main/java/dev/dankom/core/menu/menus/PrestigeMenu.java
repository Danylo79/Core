package dev.dankom.core.menu.menus;

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

public class PrestigeMenu extends Menu {
    public PrestigeMenu() {
        super("&7Prestige Icons Menu", false, 54);
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

        addPrestigeIcons(profile);

        return inventory;
    }

    public void addPrestigeIcons(Profile profile) {
        for (int i = 0; i < inventory.getSize(); i++) {
            try {
                if (PrestigeIcons.getPrestigeIcons().get(i) == null) {
                    break;
                }
                PrestigeIcon p = PrestigeIcons.getPrestigeIcons().get(i);
                ItemHelper item = new ItemHelper(Material.STAINED_GLASS_PANE, 1, (p.isUnlocked(profile) ? DyeColor.GREEN : DyeColor.RED));
                ItemMeta meta = item.getMeta();
                meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', (p.isUnlocked(profile) ? "&a" : "&c") + p.getName() + " (" + p.getPrestigeIcon() + ")"));
                meta.setLore(ListHelper.toList(ChatColor.translateAlternateColorCodes('&', (p.isUnlocked(profile) ? "&a" : "&c") + p.getNeededToUnlock() + "!")));
                item.setItemMeta(meta);
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
                new MenuManager().profileMenu.openInv(player);
            } else {
                if (PrestigeIcons.getPrestigeIcons().get(slot) != null && PrestigeIcons.getPrestigeIcons().get(slot).isUnlocked(player)) {
                    player.set("network.prestigeIcon", PrestigeIcons.getPrestigeIcons().get(slot).getPrestigeIcon());
                    player.getProfileManager().sendMessage("&aSet prestige icon to " + (String) player.get("network.prestigeIcon") + "!", player);
                    player.player.closeInventory();
                }
            }
        } catch (IndexOutOfBoundsException e) {
            return;
        }
    }
}
