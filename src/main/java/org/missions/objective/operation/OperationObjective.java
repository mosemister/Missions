package org.missions.objective.operation;

import org.missions.objective.Objective;
import org.missions.objective.ObjectiveBuilder;

import java.util.Collection;

/**
 * A Objective type for operation, see {@link OperationType} for more info
 *
 * @param <O> The OperationType type
 */
public abstract class OperationObjective<O extends OperationType> extends Objective<O> {

    /**
     * {@inheritDoc}
     */
    public OperationObjective(ObjectiveBuilder<O> builder) {
        super(builder);
    }

    /**
     * {@inheritDoc}
     */
    public OperationObjective(Objective<O> objective) {
        super(objective);
    }

    /**
     * Gets the Objectives that are linked to this operation
     *
     * @return A unmodifiable collection of the linked objectives
     */
    public abstract Collection<Objective<?>> getLinkedObjectives();
}
