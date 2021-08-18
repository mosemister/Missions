package org.missions.objective.trigger.event.events;

import org.jetbrains.annotations.NotNull;
import org.missions.objective.trigger.event.criteria.EventCriteriaMeta;

import java.util.HashSet;
import java.util.Set;

public class ListeningEvent<ET extends EventType> {

    private final @NotNull Set<EventCriteriaMeta<?>> criteria = new HashSet<>();
    private final @NotNull ET type;

    public ListeningEvent(ListeningEventBuilder<ET> builder) {
        this.type = builder.getType();
        criteria.addAll(builder.getCriteria());
    }
}
