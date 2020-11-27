package dev.dankom.core.commands;

import dev.dankom.core.guild.Guild;
import dev.dankom.core.guild.GuildManager;
import dev.dankom.core.profile.Profile;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GuildCommands implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("guild") || command.getName().equalsIgnoreCase("g")) {
            if (args.length > 0) {
                Profile profile = new Profile((Player) sender);
                if (args[0].equalsIgnoreCase("create") && profile.getRank().getId() >= 2) {
                    if (args[1].chars().count() > 6) {
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cThe name " + args[1] + " is to long! It must be less then 6 characters."));
                        return false;
                    }
                    if (GuildManager.getInstance().createGuild(profile, args[1])) {
                        Guild guild = GuildManager.getInstance().get(args[1]);
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aCreated guild " + guild.getName() + '!'));
                        profile.update();
                    } else {
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cFailed to create guild! Make sure you currently arent another guild or that guild name already exists!"));
                    }
                } else if (args[0].equalsIgnoreCase("tag")) {
                    Guild guild = GuildManager.getInstance().get(profile.getGuild().getName());
                    if (guild != null && guild.getOwner().player.getUniqueId() == ((Player) sender).getUniqueId()) {
                        GuildManager.getInstance().set(guild.getName(), "tag", args[1]);
                        profile.getProfileManager().sendMessage("&aSet guild tag to" + profile.getGuildTag() + "&a!", profile);
                        profile.update();
                    }
                }
            } else {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cNo subcommand!"));
            }
        }
        return false;
    }
}
