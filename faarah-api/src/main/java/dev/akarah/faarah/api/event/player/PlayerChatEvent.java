package dev.akarah.faarah.api.event.player;

import dev.akarah.faarah.api.entity.Entity;
import dev.akarah.faarah.api.event.CancellationToken;

public record PlayerChatEvent(
        Entity player,
        String message,
        CancellationToken token
) {
}
