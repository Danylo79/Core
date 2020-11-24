package dev.dankom.core.rank;

public enum Rank {

    NONE("NONE", "", "&7", 0),
    VIP("VIP", "&a[VIP]", "&a", 1, 1.5),
    VIP_PLUS("VIP+", "&a[VIP&6+&a]", "&a", 2, 2),
    MVP("MVP", "&b[MVP]", "&b", 3, 3.5),
    MVP_PLUS("MVP+", "&b[&aMVP+&b]", "&b", 4, 5),
    MVP_PLUS_PLUS("MVP++", "&6[MVP&0++&6]", "&6", 5, 7.5),
    YOUTUBE("YOUTUBE", "&c[&fYOUTUBE&c]", "&c", 7, 10),
    ADMIN("ADMIN", "&c[ADMIN]", "&c", 8, 10),
    HELPER("HELPER", "&9[HELPER]", "&9", 9, 10),
    OWNER("OWNER", "&e[OWNER]", "&e", 10, 10)
    ;

    private String name;
    private final String display;
    private final String color;
    private final int id;
    private final double multiplier;

    Rank(String name, String display, String color, int id) {
        this.name = name;
        this.display = display;
        this.color = color;
        this.id = id;
        this.multiplier = 1;
    }

    Rank(String name, String display, String color, int id, double multiplier) {
        this.name = name;
        this.display = display;
        this.color = color;
        this.id = id;
        this.multiplier = multiplier;
    }

    public String getDisplay() {
        return display;
    }

    public String getColor() {
        return color;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        if (!(this == Rank.NONE)) {
            return name;
        } else {
            return "";
        }
    }

    public String getNameOverride() {
        return name;
    }

    public static Rank get(int id) {
        for (Rank r : values()) {
            if (r.id == id) {
                return r;
            } else {
                continue;
            }
        }
        return Rank.NONE;
    }

    public static Rank get(String name) {
        for (Rank r : values()) {
            if (r.getName().equalsIgnoreCase(name)) {
                return r;
            } else {
                continue;
            }
        }
        return Rank.NONE;
    }

    public double getMultiplier() {
        return multiplier;
    }
}
