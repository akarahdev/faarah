package dev.akarah.faarah.api.component;

import com.mojang.serialization.Codec;
import dev.akarah.faarah.api.util.ResourceLocation;

import java.util.Optional;

public interface DataComponentType<T> {
    ResourceLocation id();
    Optional<Codec<T>> codec();

    static <T> DataComponentType<T> ofVanilla(ResourceLocation id) {
        return new Vanilla<>(id);
    }

    static <T> DataComponentType<T> of(ResourceLocation id, Codec<T> customDataCodec) {
        return new Custom<>(id, customDataCodec);
    }

    record Vanilla<T>(ResourceLocation id) implements DataComponentType<T> {
        @Override
        public Optional<Codec<T>> codec() {
            return Optional.empty();
        }

        @Override
        public String toString() {
            return this.id().toString();
        }
    }

    record Custom<T>(ResourceLocation id, Codec<T> customDataCodec) implements DataComponentType<T> {
        @Override
        public Optional<Codec<T>> codec() {
            return Optional.of(this.customDataCodec);
        }

        @Override
        public String toString() {
             return this.id().toString();
        }
    }
}
