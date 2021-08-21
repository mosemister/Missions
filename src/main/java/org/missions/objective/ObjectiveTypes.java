package org.missions.objective;

import org.missions.objective.action.block.spawn.SpawnBlockActionType;
import org.missions.objective.operation.match.all.MatchAllOperationType;
import org.missions.objective.trigger.event.EventTriggerType;

/**
 * All known ObjectiveTypes
 */
public interface ObjectiveTypes {

    /**
     * Used when a event occurs
     */
    EventTriggerType EVENT_TRIGGER = new EventTriggerType();

    /**
     * Action for spawning a block
     */
    SpawnBlockActionType SPAWN_BLOCK_ACTION = new SpawnBlockActionType();

    /**
     * Operation for checking all linked objectives are complete
     */
    MatchAllOperationType MATCH_ALL_OPERATION = new MatchAllOperationType();
}
