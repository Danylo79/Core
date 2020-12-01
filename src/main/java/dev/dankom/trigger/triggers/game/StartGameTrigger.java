package dev.dankom.trigger.triggers.game;

import dev.dankom.game.core.interfaces.IGame;
import dev.dankom.trigger.triggers.base.GameTrigger;

public class StartGameTrigger extends GameTrigger {
    public StartGameTrigger(IGame game) {
        super(game, "start");
    }
}
