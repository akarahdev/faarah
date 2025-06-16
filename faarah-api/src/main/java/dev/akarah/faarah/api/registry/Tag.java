package dev.akarah.faarah.api.registry;

import java.util.List;

public interface Tag<T> {
    List<T> values();

    interface Identified<T> extends Tag<T> {
        ResourceLocation.Typed<T> key();
    }
}
