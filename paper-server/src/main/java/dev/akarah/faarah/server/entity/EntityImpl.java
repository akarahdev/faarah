package dev.akarah.faarah.server.entity;

import dev.akarah.faarah.api.component.DataComponentMap;
import dev.akarah.faarah.api.component.DataComponentType;
import dev.akarah.faarah.api.component.DataComponents;
import dev.akarah.faarah.api.entity.Entity;
import dev.akarah.faarah.api.util.FinePosition;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtOps;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.component.CustomData;

import java.util.Optional;

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
        }
        return builder.build();
    }

    @Override
    public <T> void set(DataComponentType<T> type, T value) {
        type.codec().ifPresentOrElse(
                codec -> {
                    var cdata = this.entity.get(net.minecraft.core.component.DataComponents.CUSTOM_DATA);
                    if(cdata == null) {
                        cdata = CustomData.of(new CompoundTag());
                    }
                    cdata.update(tag ->
                            tag.put(type.id().toString(), codec.encodeStart(NbtOps.INSTANCE, value).getOrThrow()));
                    this.entity.setComponent(net.minecraft.core.component.DataComponents.CUSTOM_DATA, cdata);
                },
                () -> setRawComponent(type, value)
        );
    }

    private <T> void setRawComponent(DataComponentType<T> type, T value) {
        switch (type.id().toString()) {
            case "minecraft:health" -> {
                if(this.entity instanceof LivingEntity le) {
                    le.setHealth((float) value);
                }
            }
        }
    }

    @Override
    public <T> Optional<T> get(DataComponentType<T> type) {
        return this.components().get(type);
    }
}
