package dev.akarah.faarah.api.event.player;

import dev.akarah.faarah.api.entity.Entity;
import dev.akarah.faarah.api.event.CancellationToken;

public record PlayerPickupItemEvent(
        Entity player,
        Entity itemEntity,
        CancellationToken cancelled
) {
}
