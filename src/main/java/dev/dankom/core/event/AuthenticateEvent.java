package dev.dankom.core.event;

import org.bukkit.entity.Player;

public class AuthenticateEvent extends BaseEvent {
    private final Player player;

    public AuthenticateEvent(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }
}
