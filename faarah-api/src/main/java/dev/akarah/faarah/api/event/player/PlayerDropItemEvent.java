package dev.akarah.faarah.api.event.player;

import dev.akarah.faarah.api.entity.Entity;
import dev.akarah.faarah.api.event.CancellationToken;

public record PlayerDropItemEvent(
        Entity player
) {
}
