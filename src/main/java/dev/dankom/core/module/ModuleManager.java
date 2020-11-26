package dev.dankom.core.module;

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

    public Module getModule(Module module) {
        for (Module m : modules) {
            if (m == module) {
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
