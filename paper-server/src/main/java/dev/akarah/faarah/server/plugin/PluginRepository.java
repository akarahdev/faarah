package dev.akarah.faarah.server.plugin;

import dev.akarah.faarah.api.event.EventHandler;
import dev.akarah.faarah.api.plugin.Plugin;

import java.util.HashSet;
import java.util.ServiceLoader;
import java.util.Set;
import java.util.function.Consumer;

public class PluginRepository {
    static PluginRepository REPOSITORY = new PluginRepository();

    ServiceLoader<Plugin> pluginServiceLoader;
    Set<Plugin> plugins = new HashSet<>();
    Set<EventHandler> eventHandlers = new HashSet<>();

    public static PluginRepository getInstance() {
        return REPOSITORY;
    }

    public void loadPlugin(Plugin plugin) {
        plugins.add(plugin);
    }

    public void loadEventHandler(EventHandler eventHandler) {
        eventHandlers.add(eventHandler);
    }

    public void callEvent(Consumer<EventHandler> function) {
        eventHandlers.forEach(function);
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
