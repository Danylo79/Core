package dev.dankom.core.module;

import dev.dankom.Start;

public class ModuleHelper {
    public static Module getModule(String name) {
        return Start.getInstance().moduleManager.getModule(name);
    }

    public static Module getModule(Module module) {
        return Start.getInstance().moduleManager.getModule(module);
    }

    public static ModuleManager getModuleManager(Module module) {
        return Start.getInstance().moduleManager;
    }
}
