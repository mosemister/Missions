package org.missions.objective.action;

import org.missions.objective.Objective;
import org.missions.objective.ObjectiveBuilder;

public abstract class ActionObjective<O extends ActionType> extends Objective<O> implements Runnable {

    public ActionObjective(ObjectiveBuilder<O> builder) {
        super(builder);
    }
}
