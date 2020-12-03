package dev.dankom.core.menu.menus;

import dev.dankom.core.menu.Menu;
import dev.dankom.core.profile.Profile;
import dev.dankom.core.rank.Rank;
import dev.dankom.core.rank.format.plus.PlusColor;
import dev.dankom.core.rank.format.plus.PlusRepository;
import dev.dankom.util.ItemHelper;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.meta.ItemMeta;

public class PlusColorsMenu extends Menu {
    public PlusColorsMenu() {
        super("&7Plus Colors", false, 54);
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

        addPlusColors(profile);

        return inventory;
    }

    public void addPlusColors(Profile profile) {
        for (int i = 0; i < inventory.getSize(); i++) {
            try {
                if (PlusRepository.getPlusColors().get(i) == null) {
                    break;
                }
                if (!PlusRepository.getPlusColors().get(i).showInGUI()) {
                    continue;
                }
                PlusColor pc = PlusRepository.getPlusColors().get(i);
                ItemHelper item = pc.getIcon();
                item.setDisplayName(ChatColor.translateAlternateColorCodes('&', Rank.MVP_PLUS_PLUS.getDisplay(pc.getColor())));
                item.setLore((pc.isUnlocked(profile) ? "&aUnlocked" : "&cLocked"), pc.getUnlockString());
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
                player.getMenuManager().profileMenu.openInv(player);
            } else {
                if (PlusRepository.getPlusColors().get(slot) != null && PlusRepository.getPlusColors().get(slot).isUnlocked(player) && !player.get("network.plus.color").equals(PlusRepository.getPlusColors().get(slot).getColor())) {
                    player.set("network.plus.color", PlusRepository.getPlusColors().get(slot).getColor());
                    player.getPlayer().sendMessage("&aSet your plus color to " + player.get("network.plus.color") + "+&a!");
                    player.player.closeInventory();
                }
            }
        } catch (IndexOutOfBoundsException e) {
            return;
        }
    }
}
