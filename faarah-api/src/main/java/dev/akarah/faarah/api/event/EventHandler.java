package dev.akarah.faarah.api.event;

import dev.akarah.faarah.api.event.player.*;

public interface EventHandler {
    default void playerMove(PlayerMoveEvent event) {

    }

    default void playerSwapHands(PlayerSwapHandsEvent event) {

    }

    default void playerDropItem(PlayerDropItemEvent event) {

    }

    default void playerPickupItem(PlayerPickupItemEvent event) {

    }

    default void playerChangeSlotEvent(PlayerChangeSlotEvent event) {

    }

    default void playerChatEvent(PlayerChatEvent event) {

    }
}