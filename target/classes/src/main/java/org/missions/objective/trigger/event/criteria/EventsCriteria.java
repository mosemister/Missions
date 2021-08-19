package org.missions.objective.trigger.event.criteria;

import org.missions.objective.trigger.event.criteria.match.displayname.DisplayNameMatchesEventCriteria;

public interface EventsCriteria {

    DisplayNameMatchesEventCriteria ITEM_NAME_MATCHES = new DisplayNameMatchesEventCriteria();
}
