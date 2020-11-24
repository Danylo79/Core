package dev.dankom.core.menu;

import dev.dankom.core.profile.Profile;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.inventory.Inventory;

public class Menu {
    protected Inventory inventory;
    protected String title;
    protected boolean canTake;
    protected int amtOfSlots;

    public Menu(String title, boolean canTake, int amtOfSlots) {
        this.title = title;
        this.canTake = canTake;
        this.amtOfSlots = amtOfSlots;
    }

    public Inventory createInv(Profile profile) {
        this.inventory = Bukkit.createInventory(null, amtOfSlots, ChatColor.translateAlternateColorCodes('&', title));
        return inventory;
    }
    public void openInv(Profile profile) {
        profile.player.openInventory(inventory);
    }

    //Event Hooks
    public void onClick(Profile player, int slot) {}
    public void onOpen(Profile player) {}
    public void onClose(Profile player) {}
}
