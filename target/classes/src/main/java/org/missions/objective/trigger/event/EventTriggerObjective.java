package org.missions.objective.trigger.event;

import org.missions.objective.Objective;
import org.missions.objective.trigger.event.events.EventType;
import org.missions.objective.trigger.event.events.ListeningEvent;
import org.spongepowered.api.event.Event;

public class EventTriggerObjective<E extends Event, ET extends EventType<E>> extends Objective<EventTriggerType> {

    private final ListeningEvent<E, ET> event;

    public EventTriggerObjective(EventTriggerObjectiveBuilder builder) {
        super(builder);
        if (builder.getEvent() == null) {
            throw new IllegalArgumentException("Event cannot be null");
        }
        event = (ListeningEvent<E, ET>) builder.getEvent();
    }

    public ListeningEvent<E, ET> getEvent() {
        return this.event;
    }

    @Override
    public boolean isComplete() {
        //TODO
        throw new RuntimeException("Not implemented yet");
    }
}
