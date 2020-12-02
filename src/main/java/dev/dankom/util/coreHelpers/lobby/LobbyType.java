package dev.dankom.util.coreHelpers.lobby;

public enum LobbyType {
    MINI(10), MEGA(20);

    private final int maxPlayers;

    LobbyType(int maxPlayers) {
        this.maxPlayers = maxPlayers;
    }

    public int getMaxPlayers() {
        return maxPlayers;
    }
}
