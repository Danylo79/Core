package dev.dankom.core.commands;

import dev.dankom.core.profile.Profile;
import dev.dankom.core.rank.Rank;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class RankCommands implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("setRank")) {
            if (sender.hasPermission("core.set_rank")) {
                if (Rank.get(args[1]) != null && (Bukkit.getPlayer(args[0]) != null || Bukkit.getOfflinePlayer(args[0]) != null)) {
                    Rank rank = Rank.get(args[1]);
                    Profile profile = new Profile(args[0]);
                    profile.getProfileManager().setRank(rank, profile);
                    profile.update();
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aSet " + profile.player.getDisplayName() + "&a's rank to " + rank.getDisplay(profile) + "&a!"));
                } else {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&c" + args[1] + " is not a rank or " + args[0] + " is not a player!"));
                }
            } else {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cYou do not have the permissions to do that!"));
                return false;
            }
        } else if (command.getName().equalsIgnoreCase("rank")) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aYour rank is currently " + new Profile(sender.getName()).getRank().getNameOverride() + "&a!"));
        } else {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cNo such command!"));
            return false;
        }
        return false;
    }
}
