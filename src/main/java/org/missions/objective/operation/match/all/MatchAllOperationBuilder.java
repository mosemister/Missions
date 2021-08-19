package org.missions.objective.operation.match.all;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.missions.mission.MissionBuilder;
import org.missions.objective.Objective;
import org.missions.objective.ObjectiveBuilder;
import org.missions.objective.ObjectiveTypes;
import org.spongepowered.configurate.ConfigurationNode;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class MatchAllOperationBuilder extends ObjectiveBuilder<MatchAllOperationType> {

    protected final @NotNull Set<Objective<?>> linkedObjectives = new HashSet<>();

    public @NotNull Set<Objective<?>> getLinkedObjectives() {
        return this.linkedObjectives;
    }

    @Deprecated
    public MatchAllOperationBuilder addLink() {
        throw new RuntimeException("Must add at least one link");
    }

    public @NotNull MatchAllOperationBuilder addLink(@NotNull Objective<?>... objectives) {
        this.linkedObjectives.addAll(Arrays.asList(objectives));
        return this;
    }

    public @NotNull MatchAllOperationBuilder addLink(Collection<Objective<?>> objectives) {
        this.linkedObjectives.addAll(objectives);
        return this;
    }

    @Override
    public @NotNull MatchAllOperation build() {
        return new MatchAllOperation(this);
    }

    @Override
    public @NotNull MatchAllOperationType getType() {
        return ObjectiveTypes.MATCH_ALL_OPERATION;
    }

    @Override
    public MatchAllOperationBuilder setName(@NotNull String name) {
        return (MatchAllOperationBuilder) super.setName(name);
    }

    @Override
    public MatchAllOperationBuilder setParent(@Nullable Objective<?> parent) {
        return (MatchAllOperationBuilder) super.setParent(parent);
    }

    @Override
    public MatchAllOperationBuilder fromNode(@NotNull ConfigurationNode node, @NotNull MissionBuilder builder) {
        return (MatchAllOperationBuilder) super.fromNode(node, builder);
    }
}