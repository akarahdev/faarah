package dev.akarah.faarah.server.plugin;

import dev.akarah.faarah.api.event.EventHandler;
import dev.akarah.faarah.api.plugin.Plugin;

import java.io.IOException;
import java.lang.module.ModuleDescriptor;
import java.lang.module.ModuleFinder;
import java.lang.module.ModuleReference;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PluginLoader {
    public List<PluginSource> sources;

    private PluginLoader(List<PluginSource> sources) {
        this.sources = sources;
    }

    public static PluginLoader of() {
        return new PluginLoader(new ArrayList<>());
    }

    public static PluginLoader of(List<PluginSource> sources) {
        return new PluginLoader(sources);
    }

    public void addSource(PluginSource pluginSource) {
        this.sources.add(pluginSource);
    }

    public void loadFromDirectories(Path directory) {
        try(var stream = Files.walk(directory, 1)) {
            stream
                    .filter(Files::isRegularFile)
                    .filter(path -> path.toString().endsWith(".jar"))
                    .forEach(path -> this.addSource(PluginSource.of(path)));
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
        }
    }

    public void load() {
        for(var source : this.sources) {
            this.loadPluginFromPath(source);
        }
    }

    public void loadPluginFromPath(PluginSource pluginSource) {
        if(!this.sources.contains(pluginSource)) {
            this.sources.add(pluginSource);
        }

        try(var classLoader = URLClassLoader.newInstance(
                new URL[]{pluginSource.path.toUri().toURL()},
                ClassLoader.getSystemClassLoader()
        )) {
            loadClassesFromSource(pluginSource, classLoader);
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadClassesFromSource(PluginSource pluginSource, URLClassLoader classLoader) throws ClassNotFoundException {
        var finder = ModuleFinder.of(pluginSource.path);

        var moduleNames = finder.findAll().stream()
                .map(ModuleReference::descriptor)
                .map(ModuleDescriptor::name)
                .collect(Collectors.toSet());


        for(var moduleName : moduleNames) {
            var module = finder.find(moduleName).orElseThrow();
            var providers = module.descriptor().provides();
            for(var providerList : providers) {
                for(var provider : providerList.providers()) {
                    loadClassFromName(classLoader, provider, providerList.service());
                }
            }
        }
    }

    private void loadClassFromName(URLClassLoader classLoader, String name, String service) throws ClassNotFoundException {
        var clazz = classLoader.loadClass(name);

        switch (service) {
            case "dev.akarah.faarah.api.plugin.Plugin" -> {
                var plugin = createInstanceUnsafe(clazz);
                PluginRepository.getInstance().loadPlugin((Plugin) plugin);
            }
            case "dev.akarah.faarah.api.event.EventHandler" -> {
                var eventHandler = createInstanceUnsafe(clazz);
                PluginRepository.getInstance().loadEventHandler((EventHandler) eventHandler);
            }
            default -> {}
        }
    }

    private <T> T createInstanceUnsafe(Class<T> clazz) {
        try {
            return clazz.getConstructor().newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
