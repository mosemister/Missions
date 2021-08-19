package org.missions.objective;

import org.missions.objective.action.block.spawn.SpawnBlockActionType;
import org.missions.objective.operation.match.all.MatchAllOperationType;
import org.missions.objective.trigger.event.EventTriggerType;

/**
 * All known ObjectiveTypes
 */
public interface ObjectiveTypes {

    EventTriggerType EVENT_TRIGGER = new EventTriggerType();
    SpawnBlockActionType SPAWN_BLOCK_ACTION = new SpawnBlockActionType();

    MatchAllOperationType MATCH_ALL_OPERATION = new MatchAllOperationType();
}
