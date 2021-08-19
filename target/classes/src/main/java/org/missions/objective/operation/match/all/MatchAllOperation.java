package org.missions.objective.operation.match.all;

import org.missions.objective.Objective;
import org.missions.objective.operation.OperationObjective;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class MatchAllOperation extends OperationObjective<MatchAllOperationType> {

    private final Set<Objective<?>> linkedObjectives = new HashSet<>();

    public MatchAllOperation(MatchAllOperationBuilder builder) {
        super(builder);
        this.linkedObjectives.addAll(builder.getLinkedObjectives());
    }

    @Override
    public boolean isComplete() {
        return linkedObjectives.stream().allMatch(Objective::isComplete);
    }

    @Override
    public Collection<Objective<?>> getLinkedObjectives() {
        return Collections.unmodifiableCollection(this.linkedObjectives);
    }
}
