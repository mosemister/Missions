package org.missions.objective.trigger.event.events;

import net.kyori.adventure.text.Component;
import org.missions.Category;
import org.missions.annotations.TypesGetter;
import org.missions.objective.trigger.event.criteria.EventCriteria;
import org.spongepowered.api.event.Event;

import java.util.Set;

@TypesGetter(from = EventTypes.class)
public interface EventType extends Category {

    Component getDescription();

    Set<EventCriteria> getPossibleCriteria();

    Class<? extends Event> getListeningToEvent();
}
