package dev.dankom.game.core.game.gtx;

import dev.dankom.game.core.GameManager;
import dev.dankom.game.core.game.IMap;

public class BaseGameContext implements IGameContext {

    private final IMap map;
    private final GameManager gameManager;

    public BaseGameContext(IMap map, GameManager gameManager) {
        this.map = map;
        this.gameManager = gameManager;
    }

    @Override
    public IMap getMap() {
        return map;
    }

    @Override
    public GameManager getGameManager() {
        return gameManager;
    }
}
