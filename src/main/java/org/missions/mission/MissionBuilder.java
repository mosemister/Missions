package org.missions.mission;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.missions.objective.Objective;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public interface MissionBuilder {

    String getName();

    @NotNull MissionBuilder setName(@NotNull String name);

    @Nullable Objective<?> getRootObjective();

    @NotNull MissionBuilder setRootObjective(Objective<?> objective);

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
