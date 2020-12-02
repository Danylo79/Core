package dev.dankom.util;

import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;
import java.util.List;

public class ItemHelper extends ItemStack {
    public ItemHelper(ItemStack item) {
        super(item);
    }

    public ItemHelper(Material material, int amt) {
        super(new ItemStack(material, amt));
    }

    public ItemHelper(Material material) {
        super(new ItemStack(material, 1));
    }

    public ItemHelper(Material material, int amt, DyeColor color) {
        super(new ItemStack(material, amt, color.getData()));
    }

    public ItemMeta getMeta() {
        return getItemMeta();
    }

    public SkullMeta getSkullMeta() {
        return (SkullMeta) getItemMeta();
    }

    public void setDisplayName(String name) {
        ItemMeta meta = getItemMeta();
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));
        setItemMeta(meta);
    }

    public void setSkullOwner(String name) {
        SkullMeta meta = getSkullMeta();
        meta.setOwner(name);
        setItemMeta(meta);
    }

    public void setLore(String... lore) {
        ItemMeta meta = getItemMeta();
        List<String> lores = new ArrayList<>();
        for (String s : lore) {
            lores.add(ChatColor.translateAlternateColorCodes('&', s));
        }
        meta.setLore(lores);
        setItemMeta(meta);
    }

    public void setLore(List<String> lore) {
        ItemMeta meta = getItemMeta();
        List<String> lores = new ArrayList<>();
        for (String s : lore) {
            lores.add(ChatColor.translateAlternateColorCodes('&', s));
        }
        meta.setLore(lores);
        setItemMeta(meta);
    }
}
