package dev.akarah.faarah.server.plugin;

import dev.akarah.faarah.api.Plugin;

import java.util.HashSet;
import java.util.ServiceLoader;
import java.util.Set;

public class PluginRepository {
    static PluginRepository REPOSITORY = new PluginRepository();

    ServiceLoader<Plugin> pluginServiceLoader;
    Set<Plugin> plugins = new HashSet<>();

    public static PluginRepository getInstance() {
        return REPOSITORY;
    }

    public void loadPlugin(Plugin plugin) {
        plugins.add(plugin);
    }

    public void startupAllPlugins() {
        for(var plugin : this.plugins) {
            plugin.onStartup();
        }
    }

    public void shutdownAllPlugins() {
        for(var plugin : this.plugins) {
            plugin.onShutdown();
        }
    }

    public Set<Plugin> plugins() {
        return this.plugins;
    }
}
