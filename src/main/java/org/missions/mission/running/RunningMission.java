package org.missions.mission.running;

import org.missions.mission.Mission;
import org.missions.objective.Objective;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class RunningMission {

    private final Mission mission;
    private final Objective<?> objective;
    private final Set<UUID> players = new HashSet<>();
    

}
