package dev.dankom.util;

import dev.dankom.core.inbox.Inbox;
import dev.dankom.core.inbox.InboxEntry;
import dev.dankom.core.profile.Profile;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.List;

public class FriendHelper {
    public static void acceptFriendRequest(Player p, Player t) {
        Profile target = new Profile(t);
        Profile profile = new Profile(p);
        if ((target != null && profile != null) && (p.isValid() && t.isValid())) {
            if (target.getInbox().get(profile, "sender") != null && ((String) target.getInbox().get(profile, "type")).equalsIgnoreCase("FRIEND_REQUEST")) {

                target.getInbox().removeEntry(target);
                List<String> stringList = target.database().getStringList("profiles." + t.getUniqueId().toString() + ".friends");
                stringList.add(p.getUniqueId().toString());
                target.database().set("profiles." + t.getUniqueId().toString() + ".friends", stringList);
                target.save();

                p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&9" + getAmtOfDashes("&aYou are now friends with " + target.getFullName() + "&a!")));
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aYou are now friends with " + target.getFullName() + "&a!"));
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&9" + getAmtOfDashes("&aYou are now friends with " + target.getFullName() + "&a!")));

                t.sendMessage(ChatColor.translateAlternateColorCodes('&', "&9" + getAmtOfDashes("&aYou are now friends with " + profile.getFullName() + "&a!")));
                t.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aYou are now friends with " + profile.getFullName() + "&a!"));
                t.sendMessage(ChatColor.translateAlternateColorCodes('&', "&9" + getAmtOfDashes("&aYou are now friends with " + profile.getFullName() + "&a!")));
            } else {
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aThat person has not sent you a friend request!"));
                return;
            }
        } else {
            p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cThat is not a valid player!"));
        }
    }

    public static void sendFriendRequest(Player p, Player t) {
        Profile target = new Profile(t);
        Profile profile = new Profile(p);
        if ((target != null && profile != null) && (p.isValid() && t.isValid())) {
            target.getInbox().addToInbox(new InboxEntry(profile, target, Inbox.FRIEND_REQUEST));
            p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&9" + getAmtOfDashes("&aSent a friend request to " + target.getFullName() + "&a!")));
            p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aSent a friend request to " + target.getFullName() + "&a!"));
            p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&9" + getAmtOfDashes("&aSent a friend request to " + target.getFullName() + "&a!")));

            t.sendMessage(ChatColor.translateAlternateColorCodes('&', "&9" + getAmtOfDashes("&aFriend Request From " + target.getFullName())));
            t.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aFriend request from " + profile.getFullName()));
            t.spigot().sendMessage(
                    new ComponentBuilder("[ACCEPT]")
                            .bold(true)
                            .color(net.md_5.bungee.api.ChatColor.GREEN)
                            .event(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/f accept " + target.getName()))
                            .create()
            );
            t.sendMessage(ChatColor.translateAlternateColorCodes('&', "&9" + getAmtOfDashes("&aFriend Request From " + target.getFullName())));
        } else {
            p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cThat is not a valid player!"));
        }
    }

    public static void listFriends(Profile profile, List<Profile> friends) {
        profile.player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&9-----------------------------------"));
        for (Profile p : friends) {
            profile.player.sendMessage(ChatColor.translateAlternateColorCodes('&', p.getFullName() + " &a- " + (p.player.isOnline() ? "&aOnline" : "&cOnline")));
        }
        profile.player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&9-----------------------------------"));
    }

    private static String getAmtOfDashes(String s) {
        String out = "";
        for (int i = 0; i < (s.chars().count() / 2) + 4; i++) {
            out += "-";
        }
        return out;
    }
}
