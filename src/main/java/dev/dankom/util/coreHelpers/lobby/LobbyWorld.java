package dev.dankom.util.coreHelpers.lobby;

import dev.dankom.core.lobby.LobbyManager;
import dev.dankom.util.coreHelpers.core.CoreWorld;
import net.minecraft.server.v1_8_R1.WorldServer;
import org.bukkit.World;
import org.bukkit.craftbukkit.v1_8_R1.CraftWorld;
import org.bukkit.generator.ChunkGenerator;

public class LobbyWorld extends CoreWorld {
    private final LobbyType lobbyType;

    public LobbyWorld(LobbyType lobbyType, WorldServer world, ChunkGenerator gen, Environment env) {
        super(world, gen, env);
        this.lobbyType = lobbyType;
    }

    public LobbyManager manager() {
        return LobbyManager.getInstance();
    }

    public static LobbyWorld toLobbyWorld(LobbyType lobbyType, World world) {
        return toLobbyWorld(lobbyType, (CraftWorld) world);
    }

    public static LobbyWorld toLobbyWorld(LobbyType lobbyType, CraftWorld world) {
        return new LobbyWorld(lobbyType, world.getHandle(), world.getGenerator(), world.getEnvironment());
    }

    public LobbyType getLobbyType() {
        return lobbyType;
    }
}
