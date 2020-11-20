package dev.dankom.trigger;

import dev.dankom.Start;

import java.lang.reflect.InvocationTargetException;

public class Trigger {
    private String triggerName;

    public Trigger(String name) {
        this.triggerName = name;
        call();
    }

    public String getTriggerName() {
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
                    if (data.trigger.equalsIgnoreCase(trigger.getTriggerName())) {
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
}
