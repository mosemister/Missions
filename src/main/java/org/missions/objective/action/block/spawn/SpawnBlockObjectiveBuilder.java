package org.missions.objective.action.block.spawn;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.missions.mission.MissionBuilder;
import org.missions.objective.Objective;
import org.missions.objective.ObjectiveBuilder;
import org.missions.objective.ObjectiveTypes;
import org.spongepowered.api.ResourceKey;
import org.spongepowered.api.block.BlockState;
import org.spongepowered.api.world.Location;
import org.spongepowered.api.world.World;
import org.spongepowered.api.world.server.ServerWorld;
import org.spongepowered.configurate.ConfigurationNode;
import org.spongepowered.math.vector.Vector3i;

import java.util.Optional;

public class SpawnBlockObjectiveBuilder extends ObjectiveBuilder<SpawnBlockActionType> {

    protected Vector3i location;
    protected ResourceKey world;
    protected BlockState block;

    public Vector3i getLocation() {
        return this.location;
    }

    public SpawnBlockObjectiveBuilder setLocation(@NotNull Location<? extends World<?, ?>, ?> location) {
        this.location = location.blockPosition();
        if (location.world() instanceof ServerWorld) {
            this.world = ((ServerWorld) location.world()).key();
        }
        return this;
    }

    public SpawnBlockObjectiveBuilder setLocation(@NotNull Vector3i position) {
        this.location = position;
        return this;
    }

    public Optional<ResourceKey> getWorld() {
        return Optional.ofNullable(this.world);
    }

    public SpawnBlockObjectiveBuilder setWorld(@Nullable ResourceKey key) {
        this.world = key;
        return this;
    }

    public BlockState getBlock() {
        return block;
    }

    public SpawnBlockObjectiveBuilder setBlock(@NotNull BlockState block) {
        this.block = block;
        return this;
    }

    @Override
    public @NotNull Objective<SpawnBlockActionType> build() {
        return new SpawnBlockObjective(this);
    }

    @Override
    public @NotNull SpawnBlockActionType getType() {
        return ObjectiveTypes.SPAWN_BLOCK_ACTION;
    }

    @Override
    public SpawnBlockObjectiveBuilder setName(@NotNull String name) {
        return (SpawnBlockObjectiveBuilder) super.setName(name);
    }

    @Override
    public SpawnBlockObjectiveBuilder setParent(@Nullable Objective<?> parent) {
        return (SpawnBlockObjectiveBuilder) super.setParent(parent);
    }

    @Override
    public SpawnBlockObjectiveBuilder fromNode(@NotNull ConfigurationNode node, @NotNull MissionBuilder builder) {
        return (SpawnBlockObjectiveBuilder) super.fromNode(node, builder);
    }
}
