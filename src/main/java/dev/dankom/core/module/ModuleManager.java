package dev.dankom.core.module;

import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

public class ModuleManager {
    private List<Module> modules = new ArrayList<>();

    public void registerModule(Module module) {
        if (getModule(module.getName()) == null) {
            modules.add(module);
        }
    }

    public Module getModule(String name) {
        for (Module m : modules) {
            if (m.getName().equalsIgnoreCase(name)) {
                return m;
            } else {
                continue;
            }
        }
        return null;
    }

    public List<Module> getModules() {
        return modules;
    }
}
