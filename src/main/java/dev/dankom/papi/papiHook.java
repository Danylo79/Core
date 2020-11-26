package dev.dankom.papi;

import dev.dankom.Start;
import dev.dankom.core.module.Module;
import dev.dankom.papi.placeholders.ProfilePlaceholders;

public class papiHook extends Module {
    public papiHook() {
        super("PlaceholderAPI Hook", "PAPI Hook");

        new ProfilePlaceholders(Start.getInstance()).register();
    }
}
