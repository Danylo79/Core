package dev.dankom;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dev.dankom.core.Core;
import dev.dankom.core.cosmetics.CosmeticManager;
import dev.dankom.core.file.FileManager;
import dev.dankom.core.file.IResourceManager;
import dev.dankom.core.lobby.LobbyManager;
import dev.dankom.core.module.Module;
import dev.dankom.core.module.ModuleManager;
import dev.dankom.game.core.GameCore;
import dev.dankom.game.core.GameManager;
import dev.dankom.logger.LogLevel;
import dev.dankom.logger.Logger;
import dev.dankom.papi.papiHook;
import dev.dankom.trigger.Trigger;
import dev.dankom.trigger.TriggerManager;
import dev.dankom.trigger.triggers.hdbLoadTrigger;
import me.arcaniax.hdb.api.DatabaseLoadEvent;
import me.arcaniax.hdb.api.HeadDatabaseAPI;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.*;

public class Start extends JavaPlugin implements IResourceManager, Listener {

    public ModuleManager moduleManager;
    public TriggerManager triggerManager;
    public FileManager fileManager;
    public LobbyManager lobbyManager;
    public CosmeticManager cosmeticManager;
    public GameManager gameManager;
    public static String v = null;

    public static final Gson GSON_NON_PRETTY = new GsonBuilder().enableComplexMapKeySerialization().disableHtmlEscaping().create();
    public static final Gson GSON_PRETTY = new GsonBuilder().enableComplexMapKeySerialization().disableHtmlEscaping().setPrettyPrinting().create();

    @Override
    public void onEnable() {
        this.moduleManager = new ModuleManager();
        this.triggerManager = new TriggerManager();
        this.fileManager = new FileManager();
        this.lobbyManager = new LobbyManager();
        this.cosmeticManager = new CosmeticManager();
        this.gameManager = new GameManager();

        this.v = Bukkit.getServer().getClass().getPackage().getName();
        this.v = v.substring(v.lastIndexOf(".") + 1);

        moduleManager.registerModule(new Core());
        moduleManager.registerModule(new GameCore());
        moduleManager.registerModule(new papiHook());

        triggerManager.register(this);
        getServer().getPluginManager().registerEvents(this, this);
        getServer().getPluginManager().registerEvents(gameManager, this);

        for (Module m : moduleManager.getModules()) {
            if (m.canLoad()) {
                Logger.log(LogLevel.INFO, "Activating " + m.getName() + " module!");
                m.onEnable();
            } else {
                Logger.log(LogLevel.ERROR, "Failed to load module " + m.getName() + " because it is invalid or is missing dependencies!" + (!m.areDependenciesNeeded() ? "Dependencies: None" : "Dependencies: " + m.getDependencies().toString()));
                continue;
            }
        }

        new Trigger("startup");
    }

    @Override
    public void onDisable() {
        for (Module m : moduleManager.getModules()) {
            m.onDisable();
            Logger.log(LogLevel.INFO, "Deactivating " + m.getName() + " module!");
        }

        new Trigger("shutdown");
    }

    @EventHandler
    public void onDatabaseLoad(DatabaseLoadEvent e) {
        new hdbLoadTrigger(new HeadDatabaseAPI());
    }

    public static Start getInstance() {
        return getPlugin(Start.class);
    }

    public void saveResource(File dataFolder, String resourcePath, boolean replace) {
        if (resourcePath == null || resourcePath.equals("")) {
            throw new IllegalArgumentException("ResourcePath cannot be null or empty");
        }

        resourcePath = resourcePath.replace('\\', '/');
        InputStream in = getResource(resourcePath);
        if (in == null) {
            throw new IllegalArgumentException("The embedded resource '" + resourcePath + "' cannot be found in " + dataFolder);
        }

        File outFile = new File(dataFolder, resourcePath);
        int lastIndex = resourcePath.lastIndexOf('/');
        File outDir = new File(dataFolder, resourcePath.substring(0, lastIndex >= 0 ? lastIndex : 0));

        if (!outDir.exists()) {
            outDir.mkdirs();
        }

        try {
            if (!outFile.exists() || replace) {
                OutputStream out = new FileOutputStream(outFile);
                byte[] buf = new byte[1024];
                int len;
                while ((len = in.read(buf)) > 0) {
                    out.write(buf, 0, len);
                }
                out.close();
                in.close();
            } else {
                Logger.log(LogLevel.ERROR, "Could not save " + outFile.getName() + " to " + outFile + " because " + outFile.getName() + " already exists.");
            }
        } catch (IOException ex) {
            Logger.log(LogLevel.ERROR, "Could not save " + outFile.getName() + " to " + outFile);
        }
    }

    public TriggerManager getTriggerManager() {
        return triggerManager;
    }

    public FileManager getFileManager() {
        return fileManager;
    }
}
