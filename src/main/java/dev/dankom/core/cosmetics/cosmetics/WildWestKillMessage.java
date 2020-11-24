package dev.dankom.core.cosmetics.cosmetics;

import dev.dankom.core.cosmetics.base.KillMessage;
import org.bukkit.Material;

public class WildWestKillMessage extends KillMessage {
    public WildWestKillMessage() {
        super("Wild West", "wild_west_killmessage", "Take it back to the wild west!", Material.GOLD_PICKAXE,
                "%killed% lost a drinking contest to %killer%",
                "%killed% slipped on some hot sauce spilled by %killer%",
                "%killed% lost a gun duel to %killer%"
        );
    }
}
