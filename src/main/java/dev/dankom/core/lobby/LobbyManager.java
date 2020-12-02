package dev.dankom.core.lobby;

import dev.dankom.Start;
import dev.dankom.core.file.yml.ConfigFile;
import dev.dankom.core.menu.MenuManager;
import dev.dankom.core.profile.Profile;
import dev.dankom.util.ItemHelper;
import dev.dankom.util.MathHelper;
import dev.dankom.util.coreHelpers.core.CorePlayer;
import dev.dankom.util.coreHelpers.lobby.LobbyWorld;
import dev.dankom.util.coreHelpers.core.CoreWorld;
import me.arcaniax.hdb.api.HeadDatabaseAPI;
import org.bukkit.*;
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

import java.util.ArrayList;
import java.util.List;

public class LobbyManager implements Listener {

    private static LobbyManager instance;
    private static List<LobbyWorld> lobbyPool;
    private static LobbyWorld base;

    public static void init() {
        if (database().get("lobby") == null) {
            database().set("lobby", "world");
            database().set("x", 0);
            database().set("y", 0);
            database().set("z", 0);
            database().set("old", false);
            database().saveConfig();
            database().reloadConfig();
        }

        lobbyPool = new ArrayList<>();
        base = CoreWorld.toCoreWorld(Bukkit.getWorld((String) database().get("lobby"))).getAsLobby();
    }

    public static void shutdown() {
        for (LobbyWorld lb : lobbyPool) {
            for (Player p : lb.getPlayers()) {
                p.kickPlayer("&cServer restarting!");
            }
            lb.delete();
        }
    }

    public boolean connectPlayer(Player player) {
        LobbyWorld lobby = getOpenLobby();
        CorePlayer cp = CorePlayer.toCorePlayer(player);
        try {
            return lobby.connectPlayer(cp, database().getInt("x"), database().getInt("y"), database().getInt("z"));
        } catch (Exception e) {
            cp.kickPlayer("&cFailed to connect you to an open lobby!");
            return false;
        }
    }

    public LobbyWorld getOpenLobby() {
        for (LobbyWorld l : lobbyPool) {
            boolean maxPlayers = l.getPlayers().size() < l.getLobbyType().getMaxPlayers();
            boolean valid = l.isLobby() && l.getLobbyType() != null && !l.getName().equals("");
            if (maxPlayers && valid) {
                return l;
            } else {
                continue;
            }
        }
        return createLobby();
    }

    public LobbyWorld createLobby() {
        LobbyWorld lb = CoreWorld.toCoreWorld(base.duplicateWorld(base.getLobbyType().name().toLowerCase() + genRandId())).getAsLobby();
        lobbyPool.add(lb);
        return lb;
    }

    private int genRandId() {
        int id = MathHelper.randomInt(1000, 9999);
        boolean used = false;
        for (LobbyWorld lb : lobbyPool) {
            if (lb.getName().equalsIgnoreCase(lb.getLobbyType() + String.valueOf(id))) {
                used = true;
                break;
            } else {
                continue;
            }
        }
        if (used) {
            return genRandId();
        } else {
            return MathHelper.randomInt(1000, 9999);
        }
    }

    public static LobbyWorld getLobby(int id) {
        for (LobbyWorld lb : lobbyPool) {
            if (lb.getName().equalsIgnoreCase("mini" + id)) {
                return lb;
            } else {
                continue;
            }
        }
        return null;
    }

    @EventHandler
    public void onChangeWorld(PlayerChangedWorldEvent e) {
        String lobby = database().getString("lobbby");
        Profile profile = new Profile(e.getPlayer());
        if (lobby.equalsIgnoreCase(e.getPlayer().getWorld().getName())) {
            profile.set("network.lobby", true);
            profile.getProfileManager().refreshPlayer(true, profile);
            profile.player.teleport(new Location(profile.player.getWorld(), (double) database().get("x"), (double) database().get("y"), (double) database().get("z")));
        } else {
            profile.getProfileManager().refreshPlayer(false, profile);
            profile.set("network.lobby", false);
        }
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        String lobby = database().getString("lobbby");
        Profile profile = new Profile(e.getPlayer());
        if (lobby.equalsIgnoreCase(e.getPlayer().getWorld().getName())) {
            profile.set("network.lobby", true);
            profile.getProfileManager().refreshPlayer(true, profile);
            profile.player.teleport(new Location(profile.player.getWorld(), (double) database().get("x"), (double) database().get("y"), (double) database().get("z")));
            profile.player.setGameMode(GameMode.SURVIVAL);
            profile.player.getInventory().clear();
            profile.player.setLevel((int) profile.get("network.level"));
        } else {
            profile.set("network.lobby", false);
        }

        addLobbyItems(profile);
    }

    public void addLobbyItems(Profile profile) {
        try {
            ItemHelper first = new ItemHelper(Material.COMPASS, 1);
            first.setDisplayName("&aGame Selector &7(Right Click)");
            profile.player.getInventory().setItem(0, first);

            ItemHelper third = new ItemHelper(Material.EMERALD, 1);
            third.setDisplayName("&aShop &7(Right Click)");
            profile.player.getInventory().setItem(2, third);

            ItemHelper fifth = new ItemHelper(Material.CHEST, 1);
            fifth.setDisplayName("&aCollectibles &7(Right Click)");
            profile.player.getInventory().setItem(4, fifth);

            ItemHelper second = new ItemHelper(new HeadDatabaseAPI().getItemHead("38386"));
            second.setDisplayName("&aProfile &7(Right Click)");
            second.setSkullOwner(profile.getName());
            profile.player.getInventory().setItem(1, second);
        } catch (IllegalStateException e) {
            return;
        }
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        int slot = e.getPlayer().getInventory().getHeldItemSlot();
        click(new Profile(e.getPlayer()), slot);
    }

    public void click(Profile profile, int slot) {
        if (slot == 1) {
            new MenuManager().profileMenu.openInv(profile);
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

    public static LobbyManager getInstance() {
        if (instance == null) {
            instance = new LobbyManager();
        }
        return instance;
    }

    public boolean isInLobby(Profile player) {
        return (boolean) player.get("network.lobby");
    }

    public static ConfigFile database() {
        return Start.getInstance().getFileManager().lobbyFile;
    }
}
