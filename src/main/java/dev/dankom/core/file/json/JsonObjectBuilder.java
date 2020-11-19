package dev.dankom.core.file.json;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.List;

public class JsonObjectBuilder {
    private final JSONObject jsonObject;
    private static JsonObjectBuilder instance;

    public JsonObjectBuilder() {
        this.jsonObject = new JSONObject();
        JsonObjectBuilder.instance = this;
    }

    private static JsonObjectBuilder getInstance() {
        if (instance == null) {
            instance = new JsonObjectBuilder();
        }
        return instance;
    }

    public JsonObjectBuilder addArray(String name, List<?> contents) {
        JSONArray array = new JSONArray();
        for (Object o : contents) {
            array.add(o);
        }
        jsonObject.put(name, array);
        return getInstance();
    }

    public JsonObjectBuilder addKeyValuePair(String key, Object value) {
        jsonObject.put(key, value);
        return getInstance();
    }

    public JSONObject build() {
        return jsonObject;
    }
}

