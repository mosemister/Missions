package org.missions.objective.action.block.spawn;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.missions.objective.action.ActionObjective;
import org.spongepowered.api.ResourceKey;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.block.BlockState;
import org.spongepowered.api.world.Location;
import org.spongepowered.api.world.World;
import org.spongepowered.math.vector.Vector3i;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

/**
 * The SpawnBlockObjective is a Action which will spawn a block within the specified world
 * then mark itself complete.
 */
public class SpawnBlockObjective extends ActionObjective<SpawnBlockActionType> {

    private final @NotNull Vector3i location;
    private final @Nullable ResourceKey world;
    private final @NotNull BlockState block;
    private boolean complete;

    protected SpawnBlockObjective(SpawnBlockObjective objective) {
        super(objective);
        this.location = objective.location;
        this.world = objective.world;
        this.block = objective.block.copy();
        this.complete = objective.complete;
    }

    SpawnBlockObjective(SpawnBlockObjectiveBuilder builder) {
        super(builder);
        if (builder.getBlock() == null) {
            throw new IllegalArgumentException("Block must be set");
        }
        if (builder.getLocation() == null) {
            throw new IllegalArgumentException("Location must be set");
        }
        this.location = builder.getLocation();
        this.world = builder.getWorld().orElseGet(() -> {
            if (Sponge.isServerAvailable()) {
                return Sponge.server().worldManager().defaultWorld().key();
            }
            return null;
        });
        this.block = builder.getBlock();
    }

    @Override
    public boolean isComplete() {
        return this.complete;
    }

    @Override
    public SpawnBlockObjective copy() {
        return new SpawnBlockObjective(this);
    }

    /**
     * Gets the vector location of the block to set
     *
     * @return The XYZ of the block location
     */
    public @NotNull Vector3i getLocation() {
        return location;
    }

    /**
     * Gets the resource key of the world, @see{@link Optional#empty()} if client is being used
     *
     * @return Gets the resource key
     */
    public @NotNull Optional<ResourceKey> getWorldKey() {
        return Optional.ofNullable(this.world);
    }

    /**
     * Gets or loads the world, @see{@link Optional#empty()} if the client is being used and not loaded into a world
     *
     * @param <W> The world type
     * @param <L> The location type
     * @return The World to use
     */
    public <W extends World<W, L>, L extends Location<W, L>> @NotNull Optional<W> getWorld() {
        return this
                .getWorldKey()
                .flatMap(key -> Sponge
                        .server()
                        .worldManager()
                        .world(key)
                        .map(world -> (W) world))
                .or(() -> {
                    if (!Sponge.isClientAvailable()) {
                        return Optional.empty();
                    }
                    return Sponge
                            .client()
                            .world()
                            .map(world -> (W) world);

                });
    }

    /**
     * Gets the block to set
     *
     * @return The BlockState to set
     */
    public @NotNull BlockState getBlock() {
        return block;
    }

    @Override
    public void run() {
        runGenericHack();
    }

    private <W extends World<W, L>, L extends Location<W, L>> void runGenericHack() {
        CompletableFuture<W> cfWorld = this.<W, L>getWorld().map(world -> {
            CompletableFuture<W> cf = new CompletableFuture<>();
            cf.complete(world);
            return cf;
        }).orElseGet(() -> (CompletableFuture<W>) Sponge
                .server()
                .worldManager()
                .loadWorld(this
                        .getWorldKey()
                        .orElseThrow(() -> new RuntimeException("Somehow broke logic"))));

        cfWorld.thenAccept((world) -> {
            world.location(this.location).setBlock(this.block);
            this.complete = true;
        });
    }
}
