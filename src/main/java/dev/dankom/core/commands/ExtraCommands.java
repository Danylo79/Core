package dev.dankom.core.commands;

import dev.dankom.core.profile.Profile;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ExtraCommands implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Profile player = new Profile((Player) sender);
        Profile target = new Profile(Bukkit.getPlayer(args[0]));

        if (player != null && target != null) {
            String msg = "";
            for (int i = 1; i < args.length; i++) {
                msg += args[i];
            }
            if (command.getName().equalsIgnoreCase("msg")) {
                player.player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&l&dTo&r " + target.getFullName() + "&r&7 " + msg));
                target.player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&l&dFrom&r " + player.getFullName() + "&r&7 " + msg));
                return true;
            } else if (command.getName().equalsIgnoreCase("whisper")) {
                return true;
            }
        }
        return false;
    }
}
