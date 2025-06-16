package dev.akarah.faarah.server.plugin;

import java.nio.file.Path;

public class PluginSource {
    Path path;

    private PluginSource(Path path) {
        this.path = path;
    }

    public static PluginSource of(Path path) {
        return new PluginSource(path);
    }
}
