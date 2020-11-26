package dev.dankom.core.commands;

import dev.dankom.core.inbox.Inbox;
import dev.dankom.core.inbox.InboxEntry;
import dev.dankom.core.profile.Profile;
import dev.dankom.util.FriendHelper;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class FriendCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("f") || command.getName().equalsIgnoreCase("friend")) {
            if (args.length > 0) {
                if (Bukkit.getPlayer(args[0]) != null) {
                    FriendHelper.sendFriendRequest((Player) sender, Bukkit.getPlayer(args[0]));
                    return true;
                } else {
                    if (args[0].equalsIgnoreCase("accept")) {
                        if (Bukkit.getPlayer(args[1]) != null) {
                            FriendHelper.acceptFriendRequest((Player) sender, Bukkit.getPlayer(args[1]));
                        } else {
                            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cInvalid player!"));
                        }
                    } else if (args[0].equalsIgnoreCase("list")) {
                        FriendHelper.listFriends(new Profile((Player) sender), new Profile((Player) sender).getFriends());
                    }
                }
            } else {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cNo subcommand!"));
            }
        }
        return false;
    }
}
