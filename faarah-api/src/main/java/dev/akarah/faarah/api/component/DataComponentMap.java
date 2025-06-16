package dev.akarah.faarah.api.component;

import dev.akarah.faarah.api.util.ResourceLocation;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public interface DataComponentMap {
    <T> Optional<T> get(DataComponentType<T> type);

    static DataComponentMap.Builder builder() {
        return new Builder();
    }

    class Builder implements DataComponentMap {
        final Map<ResourceLocation, Object> map = new HashMap<>();

        @Override
        @SuppressWarnings("unchecked")
        public <T> Optional<T> get(DataComponentType<T> type) {
            return Optional.ofNullable((T) this.map.get(type.id()));
        }

        public <T> void set(DataComponentType<T> type, T value) {
            this.map.put(type.id(), value);
        }

        public DataComponentMap build() {
            return new Simple(map);
        }

        @Override
        public String toString() {
            return this.map.toString();
        }
    }

    class Simple implements DataComponentMap {
        final Map<ResourceLocation, Object> map;

        private Simple(Map<ResourceLocation, Object> map) {
            this.map = map;
        }

        @Override
        @SuppressWarnings("unchecked")
        public <T> Optional<T> get(DataComponentType<T> type) {
            return Optional.ofNullable((T) this.map.get(type.id()));
        }

        @Override
        public String toString() {
            return this.map.toString();
        }
    }
}
