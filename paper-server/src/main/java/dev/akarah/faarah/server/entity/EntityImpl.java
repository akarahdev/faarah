package dev.akarah.faarah.server.entity;

import dev.akarah.faarah.api.component.DataComponentMap;
import dev.akarah.faarah.api.component.DataComponents;
import dev.akarah.faarah.api.entity.Entity;
import dev.akarah.faarah.api.util.FinePosition;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;

public class EntityImpl implements Entity {
    net.minecraft.world.entity.Entity entity;

    public EntityImpl(net.minecraft.world.entity.Entity entity) {
        this.entity = entity;
    }

    @Override
    public FinePosition position() {
        return FinePosition.of(
                this.entity.getX(),
                this.entity.getY(),
                this.entity.getZ(),
                this.entity.getYRot(),
                this.entity.getXRot()
        );
    }

    @Override
    public void teleport(FinePosition position) {
        this.entity.teleportTo(
                position.x(),
                position.y(),
                position.z()
        );
        this.entity.forceSetRotation(position.pitch(), position.yaw());
    }

    @Override
    public void sendMessage(String message) {
        if(entity instanceof ServerPlayer serverPlayer) {
            serverPlayer.sendSystemMessage(Component.literal(message));
        }
    }

    @Override
    public DataComponentMap components() {
        var builder = DataComponentMap.builder();
        if(entity instanceof LivingEntity livingEntity) {
            builder.set(DataComponents.HEALTH, livingEntity.getHealth());
            builder.set(DataComponents.MAX_HEALTH, livingEntity.getMaxHealth());
        }
        return builder.build();
    }
}
