package dev.dankom.core.menu.menus.cosmetic;

import dev.dankom.core.cosmetics.Cosmetic;
import dev.dankom.core.cosmetics.CosmeticManager;
import dev.dankom.core.cosmetics.CosmeticType;
import dev.dankom.core.cosmetics.base.KillMessage;
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

public class ClickEffectMenu extends Menu {
    public ClickEffectMenu() {
        super("&aClick Effects", false, 54);
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

        addKillMessages(profile);

        return inventory;
    }

    public void addKillMessages(Profile profile) {
        for (int i = 0; i < inventory.getSize(); i++) {
            try {
                if (CosmeticManager.getCosmetics(CosmeticType.CLICK_EFFECT).get(i) == null) {
                    break;
                }
                KillMessage k = (KillMessage) CosmeticManager.getCosmetics(CosmeticType.CLICK_EFFECT).get(i);
                ItemHelper item = new ItemHelper(k.getIcon(), 1);
                item.setDisplayName(ChatColor.translateAlternateColorCodes('&', (k.isUnlocked(profile) ? "&a" : "&c") + k.getName()));
                List<String> lore = ListHelper.toList("&7" + k.getDesc());
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
                player.getMenuManager().cosmeticMenu.openInv(player);
            } else if (CosmeticManager.getCosmetics(CosmeticType.CLICK_EFFECT).get(slot) != null) {
                Cosmetic m = CosmeticManager.getCosmetics(CosmeticType.CLICK_EFFECT).get(slot);
                if (m.isUnlocked(player)) {
                    player.set("cosmetic." + m.getCosmeticType().getDatabaseName(), m.getName());
                }
            }
        } catch (IndexOutOfBoundsException e) {
            return;
        }
    }
}
