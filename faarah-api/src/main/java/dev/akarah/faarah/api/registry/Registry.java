package dev.akarah.faarah.api.registry;

import java.util.Optional;

public interface Registry<T> {
    Optional<T> get(ResourceLocation identifier);
    Optional<ResourceLocation> getKey(T value);
    Optional<Tag.Identified<T>> getTag(ResourceLocation identifier);

    default Optional<T> get(ResourceLocation.Typed<T> key) {
        return this.get(key.path());
    }

    default Optional<Tag.Identified<T>> getTag(ResourceLocation.Typed<T> key) {
        return this.getTag(key.path());
    }
}
