package dev.dankom.trigger.triggers.game;

import dev.dankom.game.core.interfaces.IGame;
import dev.dankom.trigger.triggers.base.GameTrigger;

public class EndGameTrigger extends GameTrigger {
    public EndGameTrigger(IGame game) {
        super(game, "end");
    }
}
