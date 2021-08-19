package org.missions.objective;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;

/**
 * Base class for all objectives. A Objective is a node within a missiion,
 *
 * @param <O> The linked type to this objective
 * @see{@link ObjectiveType} for more information
 */
public abstract class Objective<O extends ObjectiveType> {

    private final @NotNull O type;
    private final @NotNull String name;
    private final @Nullable Objective<?> parent;
    private final @NotNull Set<Objective<?>> children = new HashSet<>();

    public Objective(ObjectiveBuilder<O> builder) {
        if (builder.getType() == null) {
            throw new IllegalArgumentException("Builder must specify a type");
        }
        if (builder.getName() == null) {
            throw new IllegalArgumentException("Builder must specify a name");
        }

        this.type = builder.getType();
        this.name = builder.getName();
        this.parent = builder.getParent().orElse(null);
        if (this.parent != null) {
            this.parent.children.add(this);
        }
    }

    public abstract boolean isComplete();

    /**
     * Gets the linked {@link ObjectiveType} to this objective
     *
     * @return The linked Objective
     */
    public @NotNull O getType() {
        return this.type;
    }

    /**
     * Gets the name of this objective. This should be unique within the mission and easy to understand
     *
     * @return A String name of the objective
     */
    public @NotNull String getName() {
        return this.name;
    }

    /**
     * Gets the parent to this objective.
     * A parent should be the objective that is required to be complete
     * before this objective can be complete.
     * <p>
     * A parent may not be provided, if one isn't then this objective is the
     * root objective and can be complete at any point in time
     *
     * @return The parent objective
     */
    public @NotNull Optional<Objective<?>> getParent() {
        return Optional.ofNullable(this.parent);
    }

    /**
     * Gets the children of this objective. This is all objectives that can be
     * complete after this objective is complete
     * The children collection is automagically updated based upon other objectives that are built
     *
     * @return A unmodifiable collection of child objectives
     */
    public @NotNull Collection<Objective<?>> getChildren() {
        return Collections.unmodifiableCollection(this.children);
    }
}
