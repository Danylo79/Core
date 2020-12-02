package dev.dankom.core.commands;

import dev.dankom.core.menu.MenuManager;
import dev.dankom.core.profile.Profile;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PrestigeCommands implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("prestige")) {
            new MenuManager().menus.get(0).openInv(new Profile((Player) sender));
        }
        return false;
    }
}
