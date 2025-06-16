package dev.akarah.plugin;

import dev.akarah.faarah.api.event.EventHandler;
import dev.akarah.faarah.api.event.player.PlayerMoveEvent;

public class EventHandlerImpl implements EventHandler {
    @Override
    public void playerMoveEvent(PlayerMoveEvent event) {
        event.player().sendMessage(event.newPosition().toString());
        event.player().sendMessage(event.player().components().toString());
    }
}
