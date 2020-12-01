package dev.dankom.trigger.triggers.game;

import dev.dankom.game.core.interfaces.IGame;
import dev.dankom.trigger.triggers.base.GameTrigger;

public class FailGameTrigger extends GameTrigger {
    public FailGameTrigger(IGame game) {
        super(game, "fail");
    }
}
