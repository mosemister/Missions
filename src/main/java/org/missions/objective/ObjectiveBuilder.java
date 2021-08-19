package org.missions.objective;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.missions.mission.MissionBuilder;
import org.spongepowered.configurate.ConfigurationNode;

import java.util.List;
import java.util.Optional;

/**
 * The base class for building Objectives. This should not be instated on its own, but
 * instead created though its {@link ObjectiveType}
 *
 * @param <O> The connected {@link ObjectiveType}
 */
public abstract class ObjectiveBuilder<O extends ObjectiveType> {

    protected String name;
    protected Objective<?> parent;

    private static final Object[] NODE_NAME = {"Name"};
    private static final Object[] NODE_PARENT = {"Parent"};

    /**
     * Builds the Objective from the supplied info
     *
     * @return The build objective
     * @throws IllegalArgumentException If required information is not supplied within the builder, this will be thrown
     */
    public abstract @NotNull Objective<O> build();

    /**
     * Gets the linked type to this builder, this cannot be changed on the go
     *
     * @return The linked {@link ObjectiveType}
     */
    public abstract @NotNull O getType();

    /**
     * Gets the name currently supplied to the builder, this may return null if no name has been given
     *
     * @return The name of the objective currently supplied
     */
    public @Nullable String getName() {
        return name;
    }

    /**
     * Sets the name that will be set to the builder
     *
     * @param name The new name of the objective
     * @return The builder, for chaining
     */
    public ObjectiveBuilder<O> setName(@NotNull String name) {
        this.name = name;
        return this;
    }

    /**
     * Gets the parent objective to this objective. @see{@link Objective#getParent()} for more info
     *
     * @return The parent objective
     */
    public Optional<Objective<?>> getParent() {
        return Optional.ofNullable(parent);
    }

    /**
     * Sets the parent of this objective. @see{@link Objective#getParent()} for more info on parents
     *
     * @param parent The parent to be, this can be null if no parent is required
     * @return the builder, for chaining
     */
    public ObjectiveBuilder<O> setParent(@Nullable Objective<?> parent) {
        this.parent = parent;
        return this;
    }

    /**
     * Gets the basic information from the specified configuration node
     *
     * @param node the node holding the data
     * @return the builder, for chaining
     * @throws IllegalArgumentException Node does not contain either name or parent
     * @throws IllegalStateException    The parent objective is not contained within the MissionBuilder
     */
    public ObjectiveBuilder<O> fromNode(@NotNull ConfigurationNode node, @NotNull MissionBuilder builder) {
        @Nullable String parent = node.node(NODE_PARENT).getString();
        if (parent == null) {
            throw new IllegalArgumentException("Parent is not defined");
        }
        @Nullable String name = node.node(NODE_NAME).getString();
        if (name == null) {
            throw new IllegalArgumentException("Name is not defined");
        }

        @NotNull List<Objective<?>> objectives = builder.getObjectives();
        Objective<?> parentObj = objectives
                .parallelStream()
                .filter(obj -> obj.getName().equals(parent))
                .findAny()
                .orElseThrow(() -> new IllegalStateException(parent + " cannot be found in MissionBuilder"));
        this.setName(name);
        this.setParent(parentObj);
        return this;
    }
}
