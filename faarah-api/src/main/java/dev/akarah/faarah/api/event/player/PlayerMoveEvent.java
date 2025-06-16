package dev.akarah.faarah.api.event.player;

import dev.akarah.faarah.api.entity.Entity;
import dev.akarah.faarah.api.util.FinePosition;

public record PlayerMoveEvent(
        Entity player,
        FinePosition newPosition
) {
}
