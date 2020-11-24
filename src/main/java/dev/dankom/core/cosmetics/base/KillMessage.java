package dev.dankom.core.cosmetics.base;

import dev.dankom.core.cosmetics.Cosmetic;
import dev.dankom.core.cosmetics.CosmeticType;
import dev.dankom.core.profile.Profile;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.entity.PlayerDeathEvent;

import java.util.Random;

public class KillMessage extends Cosmetic {
    private final String[] messages;

    public KillMessage(String name, String databaseName, String desc, Material icon, String... messages) {
        super(name, databaseName, desc, CosmeticType.KILL_MESSAGE, icon);
        this.messages = messages;
    }

    @Override
    public void onKill(PlayerDeathEvent e) {
        Profile killed = new Profile(e.getEntity());
        Profile killer = new Profile(e.getEntity().getKiller());
        if (killed != null && killer != null) {
            String deathMessage = getDeathMessage();
            deathMessage.replaceAll("%killer%", killer.getFullName());
            deathMessage.replaceAll("%killed%", killed.getFullName());
            e.setDeathMessage(ChatColor.translateAlternateColorCodes('&', deathMessage));
        }
    }

    public String getDeathMessage() {
        return getMessages()[new Random().nextInt(getMessages().length)];
    }

    public String[] getMessages() {
        return messages;
    }
}
