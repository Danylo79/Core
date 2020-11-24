package dev.dankom.core.menu;

import dev.dankom.Start;
import dev.dankom.core.menu.menus.AchievementsMenu;
import dev.dankom.core.menu.menus.PrestigeMenu;
import dev.dankom.core.menu.menus.ProfileMenu;
import dev.dankom.core.menu.menus.cosmetic.CosmeticMenu;
import dev.dankom.core.profile.Profile;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;

import java.util.ArrayList;
import java.util.List;

public class MenuManager implements Listener {
    public static PrestigeMenu prestigeMenu = new PrestigeMenu();
    public static ProfileMenu profileMenu = new ProfileMenu();
    public static AchievementsMenu achievementsMenu = new AchievementsMenu();
    public static CosmeticMenu cosmeticMenu = new CosmeticMenu();
    public static List<Menu> menus = new ArrayList<>();

    public MenuManager() {
        menus.add(prestigeMenu);
        menus.add(profileMenu);
        menus.add(achievementsMenu);
        menus.add(cosmeticMenu);

        Bukkit.getPluginManager().registerEvents(this, Start.getInstance());
    }

    @EventHandler
    public void onClick(InventoryClickEvent e) {
        for (Menu m : menus) {
            if (ChatColor.stripColor(e.getClickedInventory().getTitle()).equals(ChatColor.stripColor(m.inventory.getTitle()))) {
                if (!m.canTake) {
                    e.setCancelled(true);
                }
                m.onClick(new Profile((Player) e.getWhoClicked()), e.getSlot());
            }
        }
    }

    @EventHandler
    public void onOpen(InventoryOpenEvent e) {
        for (Menu m : menus) {
            m.createInv(new Profile((Player) e.getPlayer()));
            if (ChatColor.stripColor(e.getInventory().getTitle()).equals(ChatColor.stripColor(m.inventory.getTitle()))) {
                m.onOpen(new Profile((Player) e.getPlayer()));
            }
        }
    }

    @EventHandler
    public void onClose(InventoryCloseEvent e) {
        for (Menu m : menus) {
            if (ChatColor.stripColor(e.getInventory().getTitle()).equals(ChatColor.stripColor(m.inventory.getTitle()))) {
                m.onClose(new Profile((Player) e.getPlayer()));
            }
        }
    }
}
