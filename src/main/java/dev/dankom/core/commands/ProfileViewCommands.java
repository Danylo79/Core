package dev.dankom.core.commands;

import dev.dankom.core.menu.MenuManager;
import dev.dankom.core.profile.Profile;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ProfileViewCommands implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("profile")) {
            if (args[0].equalsIgnoreCase("view")) {
                Profile viewed = new Profile(Bukkit.getPlayer(args[1]));
                new Profile((Player) sender).getMenuManager().profileViewerMenu.openInv(viewed, new Profile((Player) sender));
            }
        }
        return false;
    }
}
