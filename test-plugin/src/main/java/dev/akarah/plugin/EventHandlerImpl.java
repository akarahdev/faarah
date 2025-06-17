package dev.akarah.plugin;

import dev.akarah.faarah.api.component.DataComponents;
import dev.akarah.faarah.api.event.EventHandler;
import dev.akarah.faarah.api.event.player.PlayerMoveEvent;

public class EventHandlerImpl implements EventHandler {
    @Override
    public void playerMove(PlayerMoveEvent event) {
        event.player().sendMessage(event.newPosition().toString());
        event.player().sendMessage(event.player().get(DataComponents.HEALTH).toString());
        event.player().set(DataComponents.HEALTH, 5.0f + ((float) Math.random() * 5.0f));
    }
}
