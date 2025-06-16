package dev.akarah.faarah.api.entity;

import dev.akarah.faarah.api.component.DataComponentMap;
import dev.akarah.faarah.api.util.FinePosition;

public interface Entity {
    FinePosition position();
    void teleport(FinePosition position);

    void sendMessage(String message);

    DataComponentMap components();
}
