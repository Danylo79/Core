package dev.dankom.core.file;

import java.io.File;

public interface IResourceManager {
    void saveResource(File dataFolder, String resourcePath, boolean replace);
}
