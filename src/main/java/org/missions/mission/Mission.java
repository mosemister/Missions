package org.missions.mission;

import org.jetbrains.annotations.NotNull;
import org.missions.objective.Objective;

public class Mission {

    private final @NotNull String name;
    private final @NotNull Objective<?> objective;

    public Mission(MissionBuilder builder) {
        if (builder.getRootObjective() == null) {
            throw new IllegalArgumentException("Root objective must be specified");
        }
        if (builder.getName() == null) {
            throw new IllegalArgumentException("Name must be specified");
        }
        this.name = builder.getName();
        this.objective = builder.getRootObjective();
    }

    public String getName() {
        return name;
    }

    public Objective<?> getRootObjective() {
        return objective;
    }
}
