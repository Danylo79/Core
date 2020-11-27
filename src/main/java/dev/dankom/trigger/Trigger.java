package dev.dankom.trigger;

import dev.dankom.Start;

import java.lang.reflect.InvocationTargetException;

public class Trigger {
    private String triggerName;
    private String loc;

    public Trigger(String name, String triggerThread) {
        this.triggerName = name;
        this.loc = triggerThread;
        call();
    }

    public Trigger(String name) {
        this.triggerName = name;
        this.loc = "any";
        call();
    }

    public String getTriggerName() {
        return triggerName;
    }

    public String getLoc() {
        return triggerName;
    }

    public enum State {
        PRE("PRE", 0), POST("POST", 1);
        private State(String string, int number) {
        }
    }

    public Trigger call() {
        this.call(this);
        return this;
    }

    private static void call(Trigger trigger) {
        ArrayHelper<Data> dataList = Start.getInstance().getTriggerManager().get(trigger.getClass());
        if (dataList != null) {
            for (Data data : dataList) {
                try {
                    if (shouldTrigger(trigger, data)) {
                        data.target.invoke(data.source, trigger);
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }

            }
        }
    }

    private static boolean shouldTrigger(Trigger trigger, Data data) {
        boolean nameCorrect = data.trigger.equalsIgnoreCase(trigger.getTriggerName());
        boolean needLocCorrect = !(data.thread.equalsIgnoreCase("any") || data.thread.equalsIgnoreCase("all"));
        if (needLocCorrect) {
            boolean locCorrect = data.thread.equalsIgnoreCase(trigger.getLoc());
            return nameCorrect && locCorrect;
        } else {
            return nameCorrect;
        }
    }
}
