package dev.dankom.util.coreHelpers;

public enum Client {

    LUNAR("Lunar-Client", "Lunar Client"),
    VANILLA("Vanilla"),
    BADLION("Badlion"),
    NOT_SET("None")
    ;

    private final String channel;
    private final String name;

    Client(String channel, String name) {
        this.channel = channel;
        this.name = name;
    }

    Client(String name) {
        this.channel = "";
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
