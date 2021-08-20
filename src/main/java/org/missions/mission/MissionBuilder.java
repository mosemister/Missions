package org.missions.mission;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.missions.objective.Objective;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The class used to build Missions.
 */
public interface MissionBuilder {

    /**
     * Gets the name of the mission, this will be null if no name has been set
     *
     * @return The name of the mission
     */
    @Nullable String getName();

    /**
     * Sets the name of the mission
     *
     * @param name The new name of the mission
     * @return this builder, for chaining
     */
    @NotNull MissionBuilder setName(@NotNull String name);

    /**
     * Gets the root objective, this will be null if none has been set
     *
     * @return The root objective
     */
    @Nullable Objective<?> getRootObjective();

    /**
     * Sets the root objective of the mission
     *
     * @param objective The root objective
     * @return this builder, for chaining
     */
    @NotNull MissionBuilder setRootObjective(@NotNull Objective<?> objective);

    /**
     * Gets all objectives found within the specified objective
     *
     * @return All the objectives found within the specified objective,
     * returns {@link Collections#emptyList()} if no objective is set
     */
    default @NotNull List<Objective<?>> getObjectives() {
        @Nullable Objective<?> root = this.getRootObjective();
        if (root == null) {
            return Collections.emptyList();
        }
        List<Objective<?>> ret = new ArrayList<>();
        List<Objective<?>> prevTargets = new ArrayList<>();
        List<Objective<?>> targets = new ArrayList<>();
        targets.add(root);
        while (!targets.isEmpty()) {
            ret.addAll(prevTargets);
            prevTargets = targets;
            targets = prevTargets
                    .stream()
                    .flatMap(obj -> obj.getChildren().stream())
                    .collect(Collectors.toList());
        }
        return ret;
    }
}
