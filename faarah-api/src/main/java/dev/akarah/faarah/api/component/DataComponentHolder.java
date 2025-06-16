package dev.akarah.faarah.api.component;

public interface DataComponentHolder extends DataComponentMap {
    DataComponentMap components();
    <T> void set(DataComponentType<T> type, T value);
}
