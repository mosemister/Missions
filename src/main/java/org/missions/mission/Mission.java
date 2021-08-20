package org.missions.mission;

import org.jetbrains.annotations.NotNull;
import org.missions.objective.Objective;

/**
 * This is the class where a mission is stored.
 * If you are looking for the class for when a mission is live, try {@link org.missions.mission.running.RunningMission}
 */
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

    /**
     * Gets the friendly name of the mission
     *
     * @return The name of the mission
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the root (start) objective of the mission, this will be copied when the mission is starting to run
     *
     * @return The root objective of this mission
     */
    public Objective<?> getRootObjective() {
        return objective;
    }
}
