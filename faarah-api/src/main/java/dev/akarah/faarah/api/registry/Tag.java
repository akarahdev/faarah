package dev.akarah.faarah.api.registry;

import dev.akarah.faarah.api.util.ResourceLocation;

import java.util.List;

public interface Tag<T> {
    List<T> values();

    interface Identified<T> extends Tag<T> {
        ResourceLocation.Typed<T> key();
    }
}
