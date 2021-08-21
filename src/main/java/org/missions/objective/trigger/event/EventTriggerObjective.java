package org.missions.objective.trigger.event;

import org.jetbrains.annotations.NotNull;
import org.missions.objective.Objective;
import org.missions.objective.trigger.event.criteria.EventCriteriaMeta;
import org.missions.objective.trigger.event.events.EventType;
import org.spongepowered.api.event.Event;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * The objective for when a event is triggered
 *
 * @param <E>  The Sponge event
 * @param <ET> The Event Type to listen to
 */
public class EventTriggerObjective<E extends Event, ET extends EventType<E>> extends Objective<EventTriggerType> {

    private final ET event;
    private final @NotNull Set<EventCriteriaMeta<?, E>> criteria = new HashSet<>();


    /**
     * {@inheritDoc}
     */
    public EventTriggerObjective(EventTriggerObjectiveBuilder builder) {
        super(builder);
        if (builder.getEvent() == null) {
            throw new IllegalArgumentException("Event cannot be null");
        }
        event = (ET) builder.getEvent();
        this.criteria.addAll(builder.getCriteria().stream().map(meta -> (EventCriteriaMeta<?, E>) meta).collect(Collectors.toSet()));
    }

    /**
     * Gets the event type attached to this Event Trigger Objectives
     *
     * @return The EventType
     */
    public ET getEvent() {
        return this.event;
    }

    @Override
    public boolean isComplete() {
        //TODO
        throw new RuntimeException("Not implemented yet");
    }

    @Override
    public Objective<EventTriggerType> copy() {
        //TODO
        throw new RuntimeException("Not implemented yet");
    }
}
