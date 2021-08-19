package org.missions.objective.trigger.event.events;

import org.jetbrains.annotations.NotNull;
import org.missions.objective.trigger.event.criteria.EventCriteriaMeta;
import org.spongepowered.api.event.Event;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public abstract class ListeningEventBuilder<E extends Event, T extends EventType<E>> {

    protected final Set<EventCriteriaMeta<?, E>> criteria = new HashSet<>();

    public abstract T getType();

    public abstract ListeningEvent<E, T> build();

    public Set<EventCriteriaMeta<?, E>> getCriteria() {
        return this.criteria;
    }

    @Deprecated
    public ListeningEventBuilder<E, T> addCriteria() {
        throw new RuntimeException("Cannot add nothing");
    }

    public ListeningEventBuilder<E, T> addCriteria(@NotNull EventCriteriaMeta<?, E>... meta) {
        this.criteria.addAll(Arrays.asList(meta));
        return this;
    }
}
