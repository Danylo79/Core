package dev.dankom.core.menu;

import dev.dankom.Start;
import dev.dankom.core.guild.GuildManager;
import dev.dankom.core.menu.menus.*;
import dev.dankom.core.menu.menus.cosmetic.*;
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
    public List<Menu> menus = new ArrayList<>();
    public PrestigeMenu prestigeMenu = new PrestigeMenu();
    public ProfileMenu profileMenu = new ProfileMenu();
    public AchievementsMenu achievementsMenu = new AchievementsMenu();
    public LobbySettingsMenu lobbySettingsMenu = new LobbySettingsMenu();
    public CosmeticMenu cosmeticMenu = new CosmeticMenu();
    public ProfileViewer profileViewerMenu = new ProfileViewer();
    public NickMenu nickMenu = new NickMenu();
    //Effect Menus
    public KillMessageMenu killMessagesMenu = new KillMessageMenu();
    public ProjectileTrailMenu projectileTrailMenu = new ProjectileTrailMenu();
    public KillEffectMenu killEffectMenu = new KillEffectMenu();
    public ClickEffectMenu clickEffectMenu = new ClickEffectMenu();

    public MenuManager() {
        menus.add(prestigeMenu);
        menus.add(profileMenu);
        menus.add(achievementsMenu);
        menus.add(cosmeticMenu);
        menus.add(killMessagesMenu);
        menus.add(projectileTrailMenu);
        menus.add(clickEffectMenu);
        menus.add(killEffectMenu);
        menus.add(lobbySettingsMenu);
        menus.add(profileViewerMenu);
        menus.add(nickMenu);

        Bukkit.getPluginManager().registerEvents(this, Start.getInstance());
    }

    @EventHandler
    public void onClick(InventoryClickEvent e) {
        try {
            for (Menu m : menus) {
                if (ChatColor.stripColor(e.getClickedInventory().getTitle()).equals(ChatColor.stripColor(m.inventory.getTitle()))) {
                    if (!m.canTake) {
                        e.setCancelled(true);
                    }
                    m.onClick(new Profile((Player) e.getWhoClicked()), e.getSlot());
                }
            }
        } catch (NullPointerException nullPointerException) {
            return;
        } catch (Exception exception) {
            exception.printStackTrace();
            return;
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
