package dev.dankom.util;

import java.util.ArrayList;
import java.util.List;

public class SimpleMap<KEY, VALUE> {

    private List<SimpleMapEntry> mapEntries = new ArrayList<>();

    public void put(KEY key, VALUE value) {
        SimpleMapEntry<KEY, VALUE> entry = new SimpleMapEntry<>(key, value);
        if (mapEntries.contains(entry)) {
            mapEntries.remove(entry);
        }
        mapEntries.add(entry);
    }

    public VALUE get(KEY key) {
        for (SimpleMapEntry me : mapEntries) {
            if (me.getKey().equals(key)) {
                return (VALUE) me.getValue();
            } else {
                continue;
            }
        }
        return null;
    }

    public boolean contains(KEY key) {
        return get(key) != null;
    }

    public class SimpleMapEntry<KEY, VALUE> {
        private final KEY key;
        private final VALUE value;

        public SimpleMapEntry(KEY key, VALUE value) {
            this.key = key;
            this.value = value;
        }

        public KEY getKey() {
            return key;
        }

        public VALUE getValue() {
            return value;
        }
    }
}
