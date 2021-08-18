package org.missions.objective.trigger.event;

import org.missions.objective.Objective;
import org.missions.objective.ObjectiveBuilder;
import org.missions.objective.ObjectiveTypes;
import org.spongepowered.api.event.Event;

public class EventTriggerBuilder extends ObjectiveBuilder<EventTriggerType> {

    private Class<? extends Event> listeningFor;


    @Override
    public Objective<EventTriggerType> build() {
        if (listeningFor == null) {
            throw new IllegalArgumentException("listeningFor must be set");
        }
        return new Objective<>(this);
    }

    @Override
    public EventTriggerType getType() {
        return ObjectiveTypes.EVENT_TRIGGER;
    }
}
