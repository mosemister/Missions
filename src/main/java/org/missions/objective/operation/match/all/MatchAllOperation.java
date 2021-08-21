package org.missions.objective.operation.match.all;

import org.missions.objective.Objective;
import org.missions.objective.operation.OperationObjective;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * a objective to check if all the linked objectives are complete
 */
public class MatchAllOperation extends OperationObjective<MatchAllOperationType> {

    private final Set<Objective<?>> linkedObjectives = new HashSet<>();

    /**
     * {@inheritDoc}
     */
    public MatchAllOperation(MatchAllOperation objective) {
        super(objective);
        this.linkedObjectives.addAll(objective.linkedObjectives);
    }

    /**
     * {@inheritDoc}
     */
    public MatchAllOperation(MatchAllOperationBuilder builder) {
        super(builder);
        this.linkedObjectives.addAll(builder.getLinkedObjectives());
    }

    @Override
    public boolean isComplete() {
        return linkedObjectives.stream().allMatch(Objective::isComplete);
    }

    @Override
    public MatchAllOperation copy() {
        return new MatchAllOperation(this);
    }

    @Override
    public Collection<Objective<?>> getLinkedObjectives() {
        return Collections.unmodifiableCollection(this.linkedObjectives);
    }
}
