package dev.akarah.faarah.api.registry;

public interface WritableRegistry<T> extends Registry<T> {
    T register(ResourceLocation identifier, T value);
    T registerTag(ResourceLocation identifier, Tag<T> tag);
}
