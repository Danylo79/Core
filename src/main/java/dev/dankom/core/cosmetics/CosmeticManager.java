package dev.dankom.core.cosmetics;

import dev.dankom.core.cosmetics.cosmetics.MemedKillMessage;
import dev.dankom.core.cosmetics.cosmetics.WildWestKillMessage;
import dev.dankom.core.profile.Profile;
import dev.dankom.logger.LogLevel;
import dev.dankom.logger.Logger;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.ArrayList;
import java.util.List;

public class CosmeticManager implements Listener {
    private static List<Cosmetic> cosmetics = new ArrayList<>();

    public static List<Cosmetic> getCosmetics() {
        addCosmetics();
        return cosmetics;
    }

    private static void addCosmetics() {
        cosmetics.clear();
        //Add Cosmetics Here
        cosmetics.add(new MemedKillMessage() {
            @Override
            public boolean isUnlocked(Profile profile) {
                return (int) profile.get("network.level") >= 1000;
            }
        });
        cosmetics.add(new WildWestKillMessage());
        //
    }

    @EventHandler
    public void onClick(PlayerInteractEvent e) {
        Profile profile = new Profile(e.getPlayer());
        for (Cosmetic c : cosmetics) {
            if (c.isActivated(profile)) { c.onClick(); }
        }
    }

    @EventHandler
    public void onProjectileLaunch(ProjectileLaunchEvent e) {
        Profile profile = new Profile((Player) e.getEntity().getShooter());
        for (Cosmetic c : cosmetics) {
            if (c.isActivated(profile)) { c.onProjectileFire(e.getEntity()); }
        }
    }

    @EventHandler
    public void onProjectileHitEvent(ProjectileHitEvent e) {
        Profile profile = new Profile((Player) e.getEntity().getShooter());
        for (Cosmetic c : cosmetics) {
            if (c.isActivated(profile)) { c.onProjectileLand(e.getEntity()); }
        }
    }

    @EventHandler
    public void onPlayerKill(PlayerDeathEvent e) {
        Profile profile = new Profile((Player) e.getEntity().getKiller());
        for (Cosmetic c : cosmetics) {
            if (c.isActivated(profile)) {
                c.onKill(e.getEntity());
                c.onKill(e);
            }
        }
    }

    public static List<Cosmetic> getCosmetics(CosmeticType cosmeticType) {
        addCosmetics();
        List<Cosmetic> out = new ArrayList<>();
        for (Cosmetic c : cosmetics) {
            if (c.getCosmeticType().getDatabaseName().equalsIgnoreCase(cosmeticType.getDatabaseName())) {
                out.add(c);
            } else {
                continue;
            }
        }
        return out;
    }
}
