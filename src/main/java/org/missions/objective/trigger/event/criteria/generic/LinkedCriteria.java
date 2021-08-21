package org.missions.objective.trigger.event.criteria.generic;

import org.missions.objective.trigger.event.criteria.EventCriteria;
import org.missions.objective.trigger.event.criteria.EventCriteriaMeta;

import java.util.Collection;
import java.util.stream.Collectors;

/**
 * Criteria that is linked to other criteria, this is typically for mapping one value to another
 */
public interface LinkedCriteria extends EventCriteria {

    /**
     * The linked EventCriteria
     *
     * @return a unmodifiable collection of all linked criteria
     */
    Collection<EventCriteria> getLinkedCriteria();

    @Override
    default Collection<? extends EventCriteriaMeta<?, ?>> createMeta() {
        return this
                .getLinkedCriteria()
                .stream()
                .flatMap(ec -> ec.createMeta().stream())
                .collect(Collectors.toUnmodifiableSet());
    }

}
