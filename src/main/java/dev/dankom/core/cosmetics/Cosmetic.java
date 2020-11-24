package dev.dankom.core.cosmetics;

import dev.dankom.core.profile.Profile;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.entity.PlayerDeathEvent;

public class Cosmetic {
    private final String name;
    private final String databaseName;
    private final String desc;
    private final CosmeticType cosmeticType;
    private final Material icon;

    public Cosmetic(String name, String databaseName, String desc, CosmeticType cosmeticType, Material icon) {
        this.name = name;
        this.databaseName = databaseName;
        this.desc = desc;
        this.cosmeticType = cosmeticType;
        this.icon = icon;
    }

    public void onClick() {}
    public void onProjectileLand(Projectile p) {}
    public void onProjectileFire(Projectile p) {}
    public void onKill(Player killed) {}
    public void onKill(PlayerDeathEvent e) {}

    public boolean isUnlocked(Profile profile) {
        return false;
    }

    public boolean isActivated(Profile profile) {
        return ((String) profile.get("cosmetic." + getCosmeticType().getDatabaseName())).equalsIgnoreCase(getDatabaseName());
    }

    public String getDesc() {
        return desc;
    }

    public String getDatabaseName() {
        return databaseName;
    }

    public String getName() {
        return name;
    }

    public CosmeticType getCosmeticType() {
        return cosmeticType;
    }

    public Material getIcon() {
        return icon;
    }
}
