package dev.dankom.core;

import dev.dankom.game.core.interfaces.IGame;
import dev.dankom.trigger.Trigger;
import dev.dankom.trigger.triggers.game.EndGameTrigger;
import dev.dankom.trigger.triggers.game.FailGameTrigger;
import dev.dankom.trigger.triggers.game.StartGameTrigger;
import dev.dankom.util.typeEnum.TypeEnum;
import dev.dankom.util.typeEnum.TypeEnumEntry;

public class Triggers extends TypeEnum {

    //Trigger Threads
    public static TriggerThread GAME_THREAD = new TriggerThread("game");
    public static TriggerThread ANY_THREAD = new TriggerThread("any");
    public static TriggerThread NONE_THREAD = new TriggerThread("none");

    //Triggers
    public static TriggerType STARTUP = new TriggerType("startup", ANY_THREAD.getThread());
    public static TriggerType SHUTDOWN = new TriggerType("shutdown", ANY_THREAD.getThread());

    public static class TriggerType extends TypeEnumEntry {
        private String trigger;
        private String loc;

        public TriggerType(String trigger, String loc) {
            this.trigger = trigger;
            this.loc = loc;
        }

        public TriggerType() {}

        public void call() {
            new Trigger(trigger, loc);
        }
    }

    public static class TriggerThread extends TypeEnumEntry {
        private final String thread;

        public TriggerThread(String thread) {
            this.thread = thread;
        }

        public String getThread() {
            return thread;
        }
    }
}
