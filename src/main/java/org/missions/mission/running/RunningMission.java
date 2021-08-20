package org.missions.mission.running;

import org.jetbrains.annotations.NotNull;
import org.missions.mission.Mission;
import org.missions.objective.Objective;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class RunningMission {

    private final Mission mission;
    private final Objective<?> objective;
    private final Set<UUID> players = new HashSet<>();

    public RunningMission(@NotNull Mission mission, @NotNull Collection<UUID> players) {
        this.mission = mission;
        this.objective = mission.getRootObjective().copy();
        this.players.addAll(players);
    }
    

}
