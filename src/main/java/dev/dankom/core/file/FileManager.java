package dev.dankom.core.file;

import dev.dankom.Start;
import dev.dankom.core.file.yml.ConfigFile;

public class FileManager {
    private static Start plugin = Start.getInstance();
    public static Directory root = new Directory(Start.getInstance().getDataFolder());
    public static Directory core = new Directory(root, "core");
    public static Directory database = new Directory(core, "database");

    public static ConfigFile databaseFile = new ConfigFile("database.yml", database, plugin.getLogger(), plugin);
}
