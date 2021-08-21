package org.missions.objective.action;

import org.missions.objective.Objective;
import org.missions.objective.ObjectiveBuilder;

/**
 * The objective for actions. See {@link ActionType} for more info
 *
 * @param <O> The ActionType type
 */
public abstract class ActionObjective<O extends ActionType> extends Objective<O> implements Runnable {

    /**
     * {@inheritDoc}
     */
    public ActionObjective(ObjectiveBuilder<O> builder) {
        super(builder);
    }

    /**
     * {@inheritDoc}
     */
    public ActionObjective(Objective<O> objective) {
        super(objective);
    }
}
