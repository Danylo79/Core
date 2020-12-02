package dev.dankom.core.commands;

import dev.dankom.core.menu.MenuManager;
import dev.dankom.core.profile.Profile;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class NickCommands implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        try {
            if (command.getName().equalsIgnoreCase("nick")) {
                Profile profile = new Profile((Player) sender);
                if (profile.getRank().getId() >= 5) {
                    if (args[0].equalsIgnoreCase("reset")) {
                        profile.resetNick();
                    } else if (args[0].equalsIgnoreCase("disable")) {
                        profile.disableNick();
                    } else {
                        new MenuManager().nickMenu.openInv(profile);
                    }
                    return true;
                } else {
                    profile.getPlayer().sendMessage("&cYou do not have permission to do that command!");
                    return false;
                }
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }
}
