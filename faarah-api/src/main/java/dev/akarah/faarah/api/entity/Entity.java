package dev.akarah.faarah.api.entity;

import dev.akarah.faarah.api.component.DataComponentHolder;
import dev.akarah.faarah.api.util.FinePosition;

public interface Entity extends DataComponentHolder {
    FinePosition position();
    void teleport(FinePosition position);

    void sendMessage(String message);
}
