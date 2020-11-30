package dev.dankom.core;

import dev.dankom.Start;
import dev.dankom.core.commands.*;
import dev.dankom.core.listeners.ClientListener;
import dev.dankom.core.listeners.ProfileListener;
import dev.dankom.core.menu.MenuManager;
import dev.dankom.core.module.Module;
import dev.dankom.core.profile.Profile;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public final class Core extends Module {
    private MenuManager menuManager;
    private ClientListener clientListener;

    public Core() {
        super("Core", "&d[&bCore&d]");
    }

    @Override
    public void onEnable() {
        this.menuManager = new MenuManager();
        this.clientListener = new ClientListener();

        registerListener(new ProfileListener());
        registerListener(Start.getInstance().lobbyManager);
        registerListener(Start.getInstance().cosmeticManager);
        registerListener(clientListener);

        setCommandExecutor("setRank", new RankCommands());
        setCommandExecutor("rank", new RankCommands());

        setCommandExecutor("cosmetics", new CosmeticCommands());

        setCommandExecutor("eco", new EcoCommands());

        setCommandExecutor("guild", new GuildCommands());
        setCommandExecutor("g", new GuildCommands());

        setCommandExecutor("prestige", new PrestigeCommands());

        setCommandExecutor("lobby", new LobbyCommands());

        setCommandExecutor("profile", new ProfileViewCommands());

        setCommandExecutor("friend", new FriendCommand());
        setCommandExecutor("f", new FriendCommand());

        setCommandExecutor("msg", new ExtraCommands());
        setCommandExecutor("whisper", new ExtraCommands());

        clientListener.init();
    }

    @Override
    public void onDisable() {
        for (Player p : Bukkit.getOnlinePlayers()) {
            Profile profile = new Profile(p);
            profile.getProfileManager().refreshPlayer(profile);
        }
    }
}
