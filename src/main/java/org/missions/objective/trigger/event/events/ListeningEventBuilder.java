package org.missions.objective.trigger.event.events;

import org.missions.objective.trigger.event.criteria.EventCriteriaMeta;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public abstract class ListeningEventBuilder<T extends EventType> {

    protected final Set<EventCriteriaMeta<?>> criteria = new HashSet<>();

    public abstract T getType();

    public abstract ListeningEvent<T> build();

    public Set<EventCriteriaMeta<?>> getCriteria() {
        return this.criteria;
    }

    @Deprecated
    public ListeningEventBuilder<T> addCriteria() {
        throw new RuntimeException("Cannot add nothing");
    }

    public ListeningEventBuilder<T> addCriteria(EventCriteriaMeta<?>... meta) {
        this.criteria.addAll(Arrays.asList(meta));
        return this;
    }
}
