package dev.akarah.faarah.api.event;

import dev.akarah.faarah.api.event.player.PlayerMoveEvent;

public interface EventHandler {
    default void playerMoveEvent(PlayerMoveEvent event) {

    }
}
