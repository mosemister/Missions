package org.missions.objective.operation;

import org.missions.objective.Objective;
import org.missions.objective.ObjectiveBuilder;

import java.util.Collection;

public abstract class OperationObjective<O extends OperationType> extends Objective<O> {

    public OperationObjective(ObjectiveBuilder<O> builder) {
        super(builder);
    }

    public abstract Collection<Objective<?>> getLinkedObjectives();
}
