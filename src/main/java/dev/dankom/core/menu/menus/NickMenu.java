package dev.dankom.core.menu.menus;

import dev.dankom.core.menu.ListedMenu;
import dev.dankom.core.menu.Menu;
import dev.dankom.core.profile.Profile;
import dev.dankom.core.rank.Rank;
import dev.dankom.util.ItemHelper;
import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.meta.ItemMeta;

public class NickMenu extends Menu {

    private Profile.Nick nick = new Profile.Nick();

    public NickMenu() {
        super("&7Nick Menu", false, 54);
    }

    @Override
    public Inventory createInv(Profile profile) {
        super.createInv(profile);

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

        item = new ItemHelper(Material.STAINED_GLASS_PANE, 1, DyeColor.ORANGE);
        inventory.setItem(0, item);
        inventory.setItem(1, item);
        inventory.setItem(2, item);
        inventory.setItem(3, item);
        inventory.setItem(5, item);
        inventory.setItem(6, item);
        inventory.setItem(7, item);
        inventory.setItem(8, item);
        inventory.setItem(9, item);

        inventory.setItem(26, item);
        inventory.setItem(27, item);
        inventory.setItem(28, item);
        inventory.setItem(29, item);
        inventory.setItem(30, item);
        inventory.setItem(31, item);
        inventory.setItem(32, item);
        inventory.setItem(33, item);
        inventory.setItem(34, item);
        inventory.setItem(35, item);

        item = new ItemHelper(Material.SIGN);
        item.setLore(
                "&7Name&b: " + nick.getName(),
                "&7Rank: " + nick.getRank().getDisplay()
        );
        inventory.setItem(4, item);

        for (int i = 9; i < inventory.getSize(); i++) {
            if (Rank.get(i) == null && i <= 26) {
                break;
            }
            Rank rank = Rank.get(i);
            boolean canUse = profile.getRank().getId() >= i;
            item = new ItemHelper(Material.STAINED_CLAY, 1, (canUse ? DyeColor.GREEN : DyeColor.RED));
            item.setDisplayName(rank.getDisplay());
            inventory.setItem(i, item);
        }

        return inventory;
    }

    public void update(Profile profile) {
        openInv(profile);
    }

    @Override
    public void onClick(Profile player, int slot) {
        if (slot == 49) {
            player.player.closeInventory();
        } else if (slot == 54) {
            player.player.closeInventory();
            nick.set(player);
        } else if (Rank.get(slot) != null) {
            nick.setRank(Rank.get(slot));
        }
        update(player);
    }
}
