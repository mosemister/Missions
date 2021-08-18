package org.missions.objective;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class Objective<O extends ObjectiveType> {

    private final @NotNull O type;
    private final @NotNull String name;
    private final @Nullable Objective<?> parent;
    private final @NotNull Set<Objective<?>> children = new HashSet<>();
    private final boolean optional;

    public Objective(ObjectiveBuilder<O> builder) {
        if (builder.getType() == null) {
            throw new IllegalArgumentException("Builder must specify a type");
        }
        if (builder.getName() == null) {
            throw new IllegalArgumentException("Builder must specify a name");
        }

        this.type = builder.getType();
        this.name = builder.getName();
        this.parent = builder.getParent();
        this.optional = builder.isOptional();
    }

    public @NotNull O getType() {
        return this.type;
    }

    public @NotNull String getName() {
        return this.name;
    }

    public @NotNull Optional<Objective<?>> getParent() {
        return Optional.ofNullable(this.parent);
    }

    public @NotNull Set<Objective<?>> getChildren() {
        return this.children;
    }

    public boolean isOptional() {
        return this.optional;
    }
}
