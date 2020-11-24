package dev.dankom.core.commands;

import dev.dankom.core.cosmetics.Cosmetic;
import dev.dankom.core.cosmetics.CosmeticManager;
import dev.dankom.core.cosmetics.CosmeticType;
import dev.dankom.core.cosmetics.base.KillMessage;
import dev.dankom.core.format.ChatFormat;
import dev.dankom.core.format.Emoji;
import dev.dankom.core.format.EmojiRepository;
import dev.dankom.core.rank.Rank;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CosmeticCommands implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("cosmetics")) {
            if (args.length > 0) {
                if (args[0].equalsIgnoreCase("emoji")) {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aThe emoji's available are:"));
                    for (Emoji e : EmojiRepository.getEmojis()) {
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a" + e.getText() + " &f-&a " + e.getReplacement() + " &f- " + e.getNeededToUnlock()));
                    }
                } else if (args[0].equalsIgnoreCase("kill_messages")) {
                    sender.sendMessage("Kill Messages");
                    for (Cosmetic k : CosmeticManager.getCosmetics(CosmeticType.KILL_MESSAGE)) {
                        sender.sendMessage(k.getName() + " - " + k.getDatabaseName());
                    }
                }
            } else {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cNo subcommand!"));
            }
        }
        return false;
    }
}
