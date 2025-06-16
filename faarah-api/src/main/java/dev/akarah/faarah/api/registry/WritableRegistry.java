package dev.akarah.faarah.api.registry;

import dev.akarah.faarah.api.util.ResourceLocation;

public interface WritableRegistry<T> extends Registry<T> {
    T register(ResourceLocation identifier, T value);
    T registerTag(ResourceLocation identifier, Tag<T> tag);
}
