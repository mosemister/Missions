package org.missions.objective.trigger.event.criteria;

import org.missions.objective.trigger.event.criteria.match.displayname.ItemNameMatchesEventCriteria;

public interface EventsCriteria {

    ItemNameMatchesEventCriteria ITEM_NAME_MATCHES = new ItemNameMatchesEventCriteria();
}
