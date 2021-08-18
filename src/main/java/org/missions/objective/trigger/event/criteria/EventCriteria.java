package org.missions.objective.trigger.event.criteria;

import net.kyori.adventure.text.Component;
import org.missions.Category;
import org.missions.annotations.TypesGetter;

@TypesGetter(from = EventsCriteria.class)
public interface EventCriteria extends Category {

    Component getDescription();


}
