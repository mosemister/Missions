package org.missions.objective.trigger.event.events;

import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.NotNull;
import org.missions.Category;
import org.missions.annotations.TypesGetter;
import org.missions.objective.trigger.event.criteria.EventCriteria;
import org.spongepowered.api.event.Event;

import java.util.Set;

@TypesGetter(from = EventTypes.class)
public interface EventType<E extends Event> extends Category {

    @NotNull Component getDescription();

    @NotNull Set<EventCriteria> getPossibleCriteria();

    @NotNull Class<E> getListeningToEvent();
}
