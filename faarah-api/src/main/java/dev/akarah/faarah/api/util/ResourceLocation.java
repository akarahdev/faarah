package dev.akarah.faarah.api.util;

import dev.akarah.faarah.api.registry.Registry;

public class ResourceLocation {
    private final String namespace;
    private final String value;

    private ResourceLocation(String namespace, String value) {
        this.namespace = namespace;
        this.value = value;
    }

    public String namespace() {
        return this.namespace;
    }

    public String value() {
        return this.value;
    }

    public static ResourceLocation of(String namespace, String value) {
        return new ResourceLocation(namespace, value);
    }

    public static class Typed<T> {
        private final Registry<T> registry;
        private final ResourceLocation path;

        private Typed(Registry<T> registry, ResourceLocation path) {
            this.registry = registry;
            this.path = path;
        }

        public Registry<T> registry() {
            return this.registry;
        }

        public ResourceLocation path() {
            return this.path;
        }

        public static <T> Typed<T> of(Registry<T> registry, ResourceLocation path) {
            return new Typed<>(registry, path);
        }
    }

    @Override
    public String toString() {
        return this.namespace() + ":" + this.value();
    }
}
