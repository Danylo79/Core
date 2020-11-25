package dev.dankom.util;

import dev.dankom.util.linkedlist.Node;
import org.bukkit.ChatColor;

public class ColorUtil {
    public static String toRainbow(String string) {
        Node colors = new Node("&c");
        Node node = colors.setNext(new Node("&6"));
        node = node.setNext(new Node("&e"));
        node = node.setNext(new Node("&a"));
        node = node.setNext(new Node("&9"));
        node = node.setNext(new Node("&5"));
        node = node.setNext(new Node("&d"));
        node.setNext(colors);

        String s = "";
        node = colors;
        for (int i = 0; i < string.chars().count(); i++) {
            String c = Character.toString(string.charAt(i));
            s += node.getValue() + c;
            node = node.next();
        }
        return s;
    }

    public static String scramble(String string) {
        return ChatColor.translateAlternateColorCodes('&', "&k" + string);
    }
}
