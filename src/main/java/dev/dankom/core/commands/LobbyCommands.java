package dev.dankom.core.commands;

import dev.dankom.Start;
import dev.dankom.core.file.yml.ConfigFile;
import dev.dankom.core.lobby.LobbyManager;
import dev.dankom.core.profile.Profile;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class LobbyCommands implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("lobby")) {
            if (args.length > 0) {
                Profile profile = new Profile((Player) sender);
                if (args[0].equalsIgnoreCase("current")) {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aYou are currently " + (!(boolean) profile.get("network.lobby") ? "not in a lobby!" : "in a lobby!")));
                } else if (args[0].equalsIgnoreCase("setLobby")) {
                    if (profile.getRank().getId() > 8) {
                        ConfigFile lobbies = Start.getInstance().getFileManager().lobbyFile;
                        lobbies.getStringList("lobbies").add(profile.player.getWorld().getName());
                        lobbies.set("x", profile.player.getLocation().getX());
                        lobbies.set("y", profile.player.getLocation().getY());
                        lobbies.set("z", profile.player.getLocation().getZ());
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aSet lobby spawn to (x: " + profile.player.getLocation().getX() + " y: " + profile.player.getLocation().getY() + " z: " + profile.player.getLocation().getZ() + ")"));
                    }
                } else if (args[0].equalsIgnoreCase("fly")) {
                    if (profile.getRank().getId() > 1) {
                        profile.set("lobby.fly", !(boolean) profile.get("lobby.fly"));
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aToggled Fly!"));
                    } else {
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cYou do not have the permission to do that!"));
                    }
                } else if (args[0].equalsIgnoreCase("hide")) {
                    profile.set("lobby.hidePlayers", !(boolean) profile.get("lobby.hidePlayers"));
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aYou can now " + ((boolean) profile.get("lobby.hidePlayers") ? "&cnot &asee players!" : "see players!")));
                }
                profile.getProfileManager().refreshPlayer(Start.getInstance().lobbyManager.isInLobby(profile), profile);
            } else {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cNo subcommand!"));
            }
        }
        return false;
    }
}