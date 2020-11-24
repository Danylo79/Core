package dev.dankom.game.core.game.gtx;

import dev.dankom.game.core.GameManager;
import dev.dankom.game.core.game.IMap;

public interface IGameContext {
    IMap getMap();
    GameManager getGameManager();
}
