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

/**
 * Build for the {@link MatchAllOperation}
 */
public class MatchAllOperationBuilder extends ObjectiveBuilder<MatchAllOperationType> {

    private final @NotNull Set<Objective<?>> linkedObjectives = new HashSet<>();

    /**
     * Gets all the current linked objectives
     *
     * @return the linked objectives
     */
    public @NotNull Set<Objective<?>> getLinkedObjectives() {
        return this.linkedObjectives;
    }

    /**
     * Do not use
     *
     * @return nothing, always throws
     * @throws RuntimeException Do not used
     */
    @Deprecated
    public @NotNull MatchAllOperationBuilder addLink() {
        throw new RuntimeException("Must add at least one link");
    }

    /**
     * Adds a linked objective to the builder
     *
     * @param objectives a array of objectives
     * @return this, for chaining
     */
    public @NotNull MatchAllOperationBuilder addLink(@NotNull Objective<?>... objectives) {
        this.linkedObjectives.addAll(Arrays.asList(objectives));
        return this;
    }

    /**
     * Adds a linked objective to the builder
     *
     * @param objectives a collection of objectives
     * @return this, for chaining
     */
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
    public @NotNull MatchAllOperationBuilder setName(@NotNull String name) {
        return (MatchAllOperationBuilder) super.setName(name);
    }

    @Override
    public @NotNull MatchAllOperationBuilder setParent(@Nullable Objective<?> parent) {
        return (MatchAllOperationBuilder) super.setParent(parent);
    }

    @Override
    public @NotNull MatchAllOperationBuilder fromNode(@NotNull ConfigurationNode node, @NotNull MissionBuilder builder) {
        return (MatchAllOperationBuilder) super.fromNode(node, builder);
    }
}
