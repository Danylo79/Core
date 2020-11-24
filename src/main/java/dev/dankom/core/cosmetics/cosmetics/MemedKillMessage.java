package dev.dankom.core.cosmetics.cosmetics;

import dev.dankom.core.cosmetics.base.KillMessage;
import org.bukkit.Material;

public class MemedKillMessage extends KillMessage {
    public MemedKillMessage() {
        super("Memed", "memed_killmessage", "Meme on dem Noobs", Material.GOLD_NUGGET,
                "%killed% was memed by %killer%",
                "%killed% was yeeted by %killer%",
                "%killed% was bruh momented by %killer%"
        );
    }
}
