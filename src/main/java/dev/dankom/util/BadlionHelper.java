package dev.dankom.util;

import com.google.gson.JsonObject;

import java.util.HashMap;
import java.util.Map;

public class BadlionHelper {
    private Map<String, DisallowedMods> modsDisallowed =  new HashMap<String, DisallowedMods>();
    private static BadlionHelper instance;

    public static BadlionHelper getInstance() {
        if (instance == null) {
            instance = new BadlionHelper();
        }
        return instance;
    }

    public BadlionHelper() {
        this.instance = this;
    }

    public Map<String, DisallowedMods> getModsDisallowed() {
        return this.modsDisallowed;
    }

    private class DisallowedMods {

        private boolean disabled;
        private JsonObject extra_data;

    }
}
