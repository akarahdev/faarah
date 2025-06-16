package dev.akarah.faarah.api.component;

import dev.akarah.faarah.api.util.ResourceLocation;

public final class DataComponents {
    public static DataComponentType<Float> HEALTH =
            DataComponentType.ofVanilla(ResourceLocation.of("minecraft", "health"));
    public static DataComponentType<Float> MAX_HEALTH =
            DataComponentType.ofVanilla(ResourceLocation.of("minecraft", "max_health"));
}
