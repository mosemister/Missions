package org.missions.objective.action.block.spawn;

import net.kyori.adventure.text.Component;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.jetbrains.annotations.NotNull;
import org.missions.Missions;
import org.missions.exceptions.parse.ParseException;
import org.missions.mission.MissionBuilder;
import org.missions.objective.Objective;
import org.missions.objective.action.block.BlockActionType;
import org.spongepowered.api.ResourceKey;
import org.spongepowered.api.block.BlockState;
import org.spongepowered.configurate.ConfigurationNode;
import org.spongepowered.configurate.serialize.SerializationException;
import org.spongepowered.math.vector.Vector3i;
import org.spongepowered.plugin.PluginContainer;

import java.util.Optional;

/**
 * Action type for spawning a block in a specific location within the world
 */
public class SpawnBlockActionType implements BlockActionType {

    private final Object[] NODE_BLOCK_STATE = {"block"};
    private final Object[] NODE_LOCATION_WORLD = {"location", "world"};
    private final Object[] NODE_LOCATION_X = {"location", "x"};
    private final Object[] NODE_LOCATION_Y = {"location", "y"};
    private final Object[] NODE_LOCATION_Z = {"location", "z"};

    @Override
    public @NotNull String getName() {
        return "SpawnBlockActionType";
    }

    @Override
    public @NotNull PluginContainer getPlugin() {
        return Missions.getPlugin().getContainer();
    }

    @Override
    public @NotNull Component getDescription() {
        return Component.text("Spawns a block");
    }

    @Override
    public @NotNull SpawnBlockObjectiveBuilder createBuilder() {
        return new SpawnBlockObjectiveBuilder();
    }

    @Override
    public void serialize(@NotNull ConfigurationNode node, @NotNull Objective<?> objectiveBasic) throws SerializationException {
        if (!(objectiveBasic instanceof SpawnBlockObjective objective)) {
            throw new IllegalArgumentException("SpawnBlockActionType requires the objective to be of a SpawnBlockObjective");
        }
        @NotNull Optional<ResourceKey> opKey = objective.getWorldKey();
        @NotNull Vector3i location = objective.getLocation();

        node.node(NODE_BLOCK_STATE).set(BlockState.class, objective.getBlock());
        node.node(NODE_LOCATION_X).set(location.x());
        node.node(NODE_LOCATION_Y).set(location.y());
        node.node(NODE_LOCATION_Z).set(location.z());
        if (opKey.isPresent()) {
            node.node(NODE_LOCATION_WORLD).set(ResourceKey.class, opKey.get());
        }
    }

    @Override
    public @NotNull Objective<?> deserialize(@NotNull ConfigurationNode node, @NotNull MissionBuilder builder) throws SerializationException, ParseException {
        @Nullable BlockState blockState = node.node(NODE_BLOCK_STATE).get(BlockState.class);
        if (blockState == null) {
            throw new ParseException(node.node(NODE_BLOCK_STATE), BlockState.class, "No value present");
        }
        int x = node.node(NODE_LOCATION_X).getInt();
        int y = node.node(NODE_LOCATION_Y).getInt();
        int z = node.node(NODE_LOCATION_Z).getInt();
        @Nullable ResourceKey key = node.node(NODE_LOCATION_WORLD).get(ResourceKey.class);
        return this
                .createBuilder()
                .fromNode(node, builder)
                .setBlock(blockState)
                .setWorld(key)
                .setLocation(Vector3i.from(x, y, z))
                .build();
    }
}
