package dev.dankom.core.commands;

import dev.dankom.core.profile.Profile;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class EcoCommands implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("eco")) {
            if (args.length > 0) {
                Profile profile = new Profile(Bukkit.getPlayer(args[0]));
                if (args[1].equalsIgnoreCase("coins")) {
                    profile.getProfileManager().giveCoins(Integer.parseInt(args[2]), true, profile);
                } else {
                    profile.getProfileManager().giveXp(Integer.parseInt(args[2]), true, profile);
                }
            } else {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cInvalid Command: /eco [player] [ecoType] [amt]"));
            }
        }
        return false;
    }
}
