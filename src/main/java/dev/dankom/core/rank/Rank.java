package dev.dankom.core.rank;

public enum Rank {

    NONE("NONE", "", "&8", 0),
    VIP("VIP", "[VIP]", "&a", 1),
    VIP_PLUS("VIP+", "[VIP+]", "&a", 2),
    MVP("MVP", "[MVP]", "&b", 3),
    MVP_PLUS("MVP+", "[MVP+]", "&b", 4),
    MVP_PLUS_PLUS("MVP++", "&6[MVP&0++&6]", "&6", 5),
    YOUTUBE("YOUTUBE", "&c[&fYOUTUBE&c]", "&c", 7),
    ADMIN("ADMIN", "&c[ADMIN]", "&c", 8),
    HELPER("HELPER", "&9[HELPER]", "&9", 9),
    OWNER("OWNER", "&c[OWNER]", "&c", 10)
    ;

    private String name;
    private final String display;
    private final String color;
    private final int id;

    Rank(String name, String display, String color, int id) {
        this.name = name;
        this.display = display;
        this.color = color;
        this.id = id;
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
}
