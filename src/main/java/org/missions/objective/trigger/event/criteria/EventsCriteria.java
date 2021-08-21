package org.missions.objective.trigger.event.criteria;

import org.missions.objective.trigger.event.criteria.match.displayname.DisplayNameMatchesEventCriteria;

/**
 * All known EventCriteria types
 */
public interface EventsCriteria {

    /**
     * For if the {@link org.spongepowered.api.data.Key} of {@link org.spongepowered.api.data.Keys#DISPLAY_NAME}
     * matches the provided information within the meta
     */
    DisplayNameMatchesEventCriteria DISPLAY_NAME_MATCHES = new DisplayNameMatchesEventCriteria();
}
