package org.missions.objective.operation;

import org.missions.objective.ObjectiveType;

/**
 * OperationType is a objective type which is used to apply mathematics logic
 * to another operation/other operations. A example of this would be {@link org.missions.objective.operation.match.all.MatchAllOperationType}
 * which won't state it is complete until all linked objectives are complete
 */
public interface OperationType extends ObjectiveType {
}
