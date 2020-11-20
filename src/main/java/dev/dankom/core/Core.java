package dev.dankom.core;

import dev.dankom.core.commands.CosmeticCommands;
import dev.dankom.core.commands.EcoCommand;
import dev.dankom.core.commands.RankCommands;
import dev.dankom.core.listeners.ProfileListener;
import dev.dankom.core.module.Module;
import dev.dankom.core.profile.Profile;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public final class Core extends Module {
    public Core() {
        super("Core", "&d[&bCore&d]");
    }

    @Override
    public void onEnable() {
        registerListener(new ProfileListener());

        setCommandExecutor("setRank", new RankCommands());
        setCommandExecutor("rank", new RankCommands());
        setCommandExecutor("cosmetics", new CosmeticCommands());
        setCommandExecutor("eco", new EcoCommand());
    }

    @Override
    public void onDisable() {
        for (Player p : Bukkit.getOnlinePlayers()) {
            Profile profile = new Profile(p);
            profile.addPlayerData();
            profile.update();
        }
    }
}
