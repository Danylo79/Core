package dev.dankom.core.lobby;

import dev.dankom.Start;
import dev.dankom.core.file.yml.ConfigFile;
import dev.dankom.core.menu.MenuManager;
import dev.dankom.core.profile.Profile;
import dev.dankom.util.ItemHelper;
import me.arcaniax.hdb.api.HeadDatabaseAPI;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.List;

public class LobbyManager implements Listener {
    @EventHandler
    public void onChangeWorld(PlayerChangedWorldEvent e) {
        List<String> lobbies = database().getStringList("lobbies");
        Profile profile = new Profile(e.getPlayer());
        if (lobbies.contains(e.getPlayer().getWorld().getName())) {
            profile.set("network.lobby", true);
            profile.getProfileManager().refreshPlayer(true, profile);
            profile.player.teleport(new Location(profile.player.getWorld(), (double) database().get("x"), (double) database().get("y"), (double) database().get("z")));
        } else {
            profile.set("network.lobby", false);
        }
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        List<String> lobbies = database().getStringList("lobbies");
        Profile profile = new Profile(e.getPlayer());
        if (lobbies.contains(e.getPlayer().getWorld().getName())) {
            profile.set("network.lobby", true);
            profile.getProfileManager().refreshPlayer(true, profile);
            profile.player.teleport(new Location(profile.player.getWorld(), (double) database().get("x"), (double) database().get("y"), (double) database().get("z")));
            profile.player.setGameMode(GameMode.SURVIVAL);
            profile.player.getInventory().clear();
        } else {
            profile.set("network.lobby", false);
        }

        addLobbyItems(profile);
    }

    public void addLobbyItems(Profile profile) {
        try {
            ItemHelper second = new ItemHelper(new HeadDatabaseAPI().getItemHead("38386"));
            second.setDisplayName("&aProfile &7(Right Click)");
            second.setSkullOwner(profile.getName());
            profile.player.getInventory().setItem(1, second);
        } catch (Exception e) {
            addLobbyItems(profile);
        }
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        int slot = e.getPlayer().getInventory().getHeldItemSlot();
        click(new Profile(e.getPlayer()), slot);
    }

    public void click(Profile profile, int slot) {
        if (slot == 1) {
            MenuManager.profileMenu.openInv(profile);
        }
    }

    @EventHandler
    public void onClick(InventoryClickEvent e) {
        Profile profile = new Profile((Player) e.getWhoClicked());
        if (isInLobby(profile) && !(profile.getRank().getId() > 8 && profile.player.getGameMode() == GameMode.CREATIVE)) {
            e.setCancelled(true);
            click(new Profile((Player) e.getWhoClicked()), e.getSlot());
        }
    }

    @EventHandler
    public void onBreak(BlockBreakEvent e) {
        Profile profile = new Profile(e.getPlayer());
        if (isInLobby(profile) && !(profile.getRank().getId() > 8 && profile.player.getGameMode() == GameMode.CREATIVE)) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onPlace(BlockPlaceEvent e) {
        Profile profile = new Profile(e.getPlayer());
        if (isInLobby(profile) && !(profile.getRank().getId() > 8 && profile.player.getGameMode() == GameMode.CREATIVE)) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onHunger(FoodLevelChangeEvent e) {
        Profile profile = new Profile((Player) e.getEntity());
        if (isInLobby(profile)) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onDamage(EntityDamageEvent e) {
        if (e.getEntity() instanceof Player) {
            Profile profile = new Profile((Player) e.getEntity());
            if (isInLobby(profile)) {
                e.setCancelled(true);
            }
        }
    }

    public boolean isInLobby(Profile player) {
        return (boolean) player.get("network.lobby");
    }

    public ConfigFile database() {
        return Start.getInstance().getFileManager().lobbyFile;
    }
}