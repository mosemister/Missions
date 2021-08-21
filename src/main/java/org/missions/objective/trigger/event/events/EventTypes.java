package org.missions.objective.trigger.event.events;

import org.missions.objective.trigger.event.criteria.block.change.PreviousBlockEventCriteria;
import org.missions.objective.trigger.event.events.block.BlockChangeEventType;

/**
 * All known EventTypes
 */
public interface EventTypes {

    /**
     * The event type for when a change to a block occurs, this could be a block growing, breaking, etc.
     */
    BlockChangeEventType BLOCK_CHANGE = new BlockChangeEventType();

    /**
     * A mapping for when a BlockTransaction occurs within the event, in this case the previous block within the transaction
     */
    PreviousBlockEventCriteria ABSTRACT_PREVIOUS_BLOCK = new PreviousBlockEventCriteria();
}
