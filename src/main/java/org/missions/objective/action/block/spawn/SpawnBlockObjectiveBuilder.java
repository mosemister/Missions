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

/**
 * The builder for the {@link SpawnBlockObjective}
 */
public class SpawnBlockObjectiveBuilder extends ObjectiveBuilder<SpawnBlockActionType> {

    private Vector3i location;
    private ResourceKey world;
    private BlockState block;

    /**
     * Gets the vector of the location to spawn the block
     *
     * @return A Vector3i of the block location
     */
    public @Nullable Vector3i getLocation() {
        return this.location;
    }

    /**
     * Sets the vector and world from the location
     *
     * @param location the location to use
     * @return this builder, for chaining
     */
    public @NotNull SpawnBlockObjectiveBuilder setLocation(@NotNull Location<? extends World<?, ?>, ?> location) {
        this.location = location.blockPosition();
        if (location.world() instanceof ServerWorld) {
            this.world = ((ServerWorld) location.world()).key();
        }
        return this;
    }

    /**
     * Sets the vector of the block location
     *
     * @param position The vector to use
     * @return this builder, for chaining
     */
    public @NotNull SpawnBlockObjectiveBuilder setLocation(@NotNull Vector3i position) {
        this.location = position;
        return this;
    }

    /**
     * Gets the world to use, this will be {@link Optional#empty()} if the Client is being used
     *
     * @return Gets the world resource key
     */
    public @NotNull Optional<ResourceKey> getWorld() {
        return Optional.ofNullable(this.world);
    }

    /**
     * Sets the world's resource key to use (client should be null)
     *
     * @param key The world key
     * @return this builder, for chaining
     */
    public @NotNull SpawnBlockObjectiveBuilder setWorld(@Nullable ResourceKey key) {
        this.world = key;
        return this;
    }

    /**
     * Gets the block to set, null if not set
     *
     * @return gets the blockState
     */
    public BlockState getBlock() {
        return block;
    }

    /**
     * sets the blockstate
     *
     * @param block the block to set
     * @return this builder, for chaining
     */
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
    public @NotNull SpawnBlockObjectiveBuilder setName(@NotNull String name) {
        return (SpawnBlockObjectiveBuilder) super.setName(name);
    }

    @Override
    public @NotNull SpawnBlockObjectiveBuilder setParent(@Nullable Objective<?> parent) {
        return (SpawnBlockObjectiveBuilder) super.setParent(parent);
    }

    @Override
    public @NotNull SpawnBlockObjectiveBuilder fromNode(@NotNull ConfigurationNode node, @NotNull MissionBuilder builder) {
        return (SpawnBlockObjectiveBuilder) super.fromNode(node, builder);
    }
}
