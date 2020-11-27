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
                if (msg.equalsIgnoreCase("")) {
                    msg += args[i];
                } else {
                    msg += " " + args[i];
                }
            }

            if (command.getName().equalsIgnoreCase("msg")) {
                if (target.getFriends().contains(player)) {
                    player.player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&dTo&r " + target.getFullName() + "&r&7 " + msg));
                    target.player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&dFrom&r " + player.getFullName() + "&r&7 " + msg));
                    return true;
                } else {
                    player.player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cYou can't message this person!"));
                    return false;
                }
            } else if (command.getName().equalsIgnoreCase("whisper")) {
                if (target.getFriends().contains(player)) {
                    player.player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6Whispered To&r " + target.getFullName() + "&r&7 " + msg));
                    target.player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6Whisper From&r " + player.getFullName() + "&r&7 " + msg));
                    return true;
                } else {
                    player.player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cYou can't whisper to this person!"));
                    return false;
                }
            }
        }
        return false;
    }
}
