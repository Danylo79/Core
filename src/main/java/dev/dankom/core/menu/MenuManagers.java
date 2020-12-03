package dev.dankom.core.menu;

import dev.dankom.core.profile.Profile;
import dev.dankom.util.SimpleMap;

import java.util.UUID;

public class MenuManagers {
    private static SimpleMap<UUID, MenuManager> map = new SimpleMap<>();

    public static MenuManager getMenuManager(Profile profile) {
        UUID uuid = profile.player.getUniqueId();
        if (!map.contains(uuid)) {
            map.put(uuid, new MenuManager(profile));
        }
        return map.get(uuid);
    }
}
