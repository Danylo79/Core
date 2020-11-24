package dev.dankom.game.core.game;

import dev.dankom.core.profile.Profile;
import dev.dankom.game.core.game.gtx.IGameContext;
import org.apache.commons.io.FileUtils;
import org.bukkit.Location;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.PlayerDeathEvent;

import java.io.IOException;
import java.util.UUID;

public class Game {
    private final Profile p1;
    private final Profile p2;
    private final IGameContext gtx;
    private final UUID id;

    private final Location prevLocP1;
    private final Location prevLocP2;

    public Game(Profile p1, Profile p2, IGameContext gtx) {
        this.p1 = p1;
        this.p2 = p2;
        this.gtx = gtx;
        this.id = UUID.randomUUID();
        this.prevLocP1 = p1.player.getLocation();
        this.prevLocP2 = p2.player.getLocation();
        preInit();
    }

    public void copyWorld() {
        try {
            if (getGTX().getMap().copyOnCreate()) {
                FileUtils.copyDirectory(getGTX().getMap().getFile(), getGTX().getMap().getFile().getParentFile());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void preInit() {
        copyWorld();
        init();
    }

    public void init() {
        onStart(getP1(), getP2());
    }

    //Event Hooks
    public void onKill(PlayerDeathEvent e) {
        if (e.getEntity() == getP1().player) {
            onFinish(getP2(), getP1());
        } else {
            onFinish(getP1(), getP2());
        }
    }
    public void onPlace(BlockPlaceEvent e) {}
    public void onBreak(BlockBreakEvent e) {}

    public void onStart(Profile p1, Profile p2) {
        p1.player.teleport(getGTX().getMap().getP1Spawn());
        p2.player.teleport(getGTX().getMap().getP2Spawn());
    }
    public void onFinish(Profile winner, Profile loser) {
        p1.player.teleport(prevLocP1);
        p2.player.teleport(prevLocP2);
        getGTX().getGameManager().finishGame(this);
    }

    public Profile getP1() {
        return p1;
    }

    public Profile getP2() {
        return p2;
    }

    public IGameContext getGTX() {
        return gtx;
    }

    public UUID getId() {
        return id;
    }
}
