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

public class SpawnBlockObjective extends ActionObjective<SpawnBlockActionType> {

    private final @NotNull Vector3i location;
    private final @Nullable ResourceKey world;
    private final @NotNull BlockState block;
    private boolean complete;

    public SpawnBlockObjective(SpawnBlockObjectiveBuilder builder) {
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

    public @NotNull Vector3i getLocation() {
        return location;
    }

    public @NotNull Optional<ResourceKey> getWorldKey() {
        return Optional.ofNullable(this.world);
    }

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
