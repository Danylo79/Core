package dev.dankom.core.file;

import dev.dankom.Start;
import dev.dankom.core.file.json.JsonFile;
import dev.dankom.core.file.json.JsonObjectBuilder;

import java.util.ArrayList;

public class FileManager {
    public static Directory root = new Directory(Start.getInstance().getDataFolder());
    public static Directory core = new Directory(root, "core");
    public static Directory database = new Directory(core, "database");

    public static JsonFile databaseFile = new JsonFile(database, "database",
            new JsonObjectBuilder()
                    .addArray("players", new ArrayList<>())
                    .build()
    );
}
