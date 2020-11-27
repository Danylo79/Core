package dev.dankom.core;

import dev.dankom.util.typeEnum.TypeEnum;
import dev.dankom.util.typeEnum.TypeEnumEntry;

public class Triggers extends TypeEnum {

    public static TriggerType STARTUP = new TriggerType("any");
    public static TriggerType SHUTDOWN = new TriggerType("any");

    public static class TriggerType extends TypeEnumEntry {
        private final String loc;

        public TriggerType(String loc) {
            this.loc = loc;
        }

        public String getLoc() {
            return loc;
        }
    }
}
