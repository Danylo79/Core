package dev.dankom;

import dev.dankom.core.Core;
import dev.dankom.core.module.Module;
import dev.dankom.core.module.ModuleManager;
import dev.dankom.logger.LogLevel;
import dev.dankom.logger.Logger;
import org.bukkit.plugin.java.JavaPlugin;

public class Start extends JavaPlugin {

    private ModuleManager moduleManager;

    @Override
    public void onEnable() {
        this.moduleManager = new ModuleManager();

        moduleManager.registerModule(new Core());

        for (Module m : moduleManager.getModules()) {
            Logger.log(LogLevel.INFO, "Activating " + m.getName() + " module!");
            m.onEnable();
        }
    }

    @Override
    public void onDisable() {
        for (Module m : moduleManager.getModules()) {
            m.onDisable();
            Logger.log(LogLevel.INFO, "Deactivating " + m.getName() + " module!");
        }
    }

    public static Start getInstance() {
        return getPlugin(Start.class);
    }
}
