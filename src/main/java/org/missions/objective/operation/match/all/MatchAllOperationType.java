package org.missions.objective.operation.match.all;

import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.NotNull;
import org.missions.Missions;
import org.missions.exceptions.parse.ParseException;
import org.missions.mission.MissionBuilder;
import org.missions.objective.Objective;
import org.missions.objective.operation.OperationType;
import org.spongepowered.configurate.ConfigurationNode;
import org.spongepowered.configurate.serialize.SerializationException;
import org.spongepowered.plugin.PluginContainer;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class MatchAllOperationType implements OperationType {

    private final Object[] NODE_LINKED_OBJECTIVES = {"Linked"};

    @Override
    public @NotNull String getName() {
        return "All Complete";
    }

    @Override
    public @NotNull PluginContainer getPlugin() {
        return Missions.getPlugin().getContainer();
    }

    @Override
    public @NotNull Component getDescription() {
        return Component.text("All linked objectives must be complete before moving on");
    }

    @Override
    public @NotNull MatchAllOperationBuilder createBuilder() {
        return new MatchAllOperationBuilder();
    }

    @Override
    public void serialize(@NotNull ConfigurationNode node, @NotNull Objective<?> objective) throws SerializationException, ParseException {
        if (!(objective instanceof MatchAllOperation operation)) {
            throw new IllegalArgumentException("objective must be of type MatchAllOperation");
        }
        List<String> linked = operation.getLinkedObjectives().parallelStream().map(Objective::getName).sorted().collect(Collectors.toList());
        node.node(NODE_LINKED_OBJECTIVES).set(linked);
    }

    @Override
    public @NotNull MatchAllOperation deserialize(@NotNull ConfigurationNode node, @NotNull MissionBuilder builder) throws SerializationException, ParseException {
        @NotNull List<Objective<?>> objectives = builder.getObjectives();
        Set<Objective<?>> linked = node
                .node(NODE_LINKED_OBJECTIVES)
                .childrenList()
                .parallelStream()
                .map(ConfigurationNode::getString)
                .flatMap(name -> objectives.parallelStream().filter(obj -> obj.getName().equals(name)))
                .collect(Collectors.toSet());

        return this.createBuilder().fromNode(node, builder).addLink(linked).build();
    }
}
