package dev.dankom.core.cosmetics;

public enum CosmeticType {
    PROJECTILE_TRAIL("Projectile Trail", "projectile_trail"),
    CLICK_EFFECT("Click Effect", "click_effect"),
    KILL_EFFECT("Kill Effect", "kill_effect"),
    KILL_MESSAGE("Kill Message", "kill_message");

    private final String name;
    private final String databaseName;

    CosmeticType(String name, String databaseName) {
        this.name = name;
        this.databaseName = databaseName;
    }

    public String getName() {
        return name;
    }

    public String getDatabaseName() {
        return databaseName;
    }
}
