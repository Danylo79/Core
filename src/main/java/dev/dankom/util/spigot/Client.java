package dev.dankom.util.spigot;

public enum Client {

    LUNAR_CLIENT("Lunar-Client", "Lunar Client"),
    VANILLA("", "Vanilla")
    ;

    private final String channel;
    private final String name;

    Client(String channel, String name) {
        this.channel = channel;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getChannel() {
        return channel;
    }

    public static Client getClient(String channel) {
        for (Client c : values()) {
            if (c.getChannel().equalsIgnoreCase(channel)) {
                return c;
            } else {
                continue;
            }
        }
        return Client.VANILLA;
    }
}
