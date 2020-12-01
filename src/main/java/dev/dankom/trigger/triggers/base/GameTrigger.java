package dev.dankom.trigger.triggers.base;

import dev.dankom.core.Triggers;
import dev.dankom.game.core.interfaces.IGame;
import dev.dankom.trigger.Trigger;

public class GameTrigger extends Trigger {
    private final IGame game;

    public GameTrigger(IGame game, String identifier) {
        super(identifier + "-game" + identifier, Triggers.GAME_THREAD.getThread());
        this.game = game;
    }

    public IGame getGame() {
        return game;
    }
}
