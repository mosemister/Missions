package org.missions.objective.trigger.event.events;

import org.jetbrains.annotations.NotNull;
import org.missions.objective.trigger.event.criteria.EventCriteriaMeta;
import org.spongepowered.api.event.Event;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class ListeningEvent<E extends Event, ET extends EventType<E>> {

    private final @NotNull Set<EventCriteriaMeta<?, E>> criteria = new HashSet<>();
    private final @NotNull ET type;

    public ListeningEvent(@NotNull ListeningEventBuilder<E, ET> builder) {
        this.type = builder.getType();
        criteria.addAll(builder.getCriteria());
    }

    public boolean isCriteriaMet(E event) {
        return getCriteria().stream().allMatch(criteria -> criteria.isCriteriaMet(event));
    }

    public @NotNull Collection<EventCriteriaMeta<?, E>> getCriteria() {
        return Collections.unmodifiableCollection(criteria);
    }

    public @NotNull ET getType() {
        return type;
    }
}
