package dev.dankom.trigger;

import java.lang.reflect.Method;

public class Data {

    public final Object source;
    public final Method target;
    public final String trigger;
    public final String thread;
    public final byte priority;

    public Data(Object source, Method target, String trigger, String thread, byte priority) {
        this.source = source;
        this.target = target;
        this.trigger = trigger;
        this.thread = thread;
        this.priority = priority;
    }
}
