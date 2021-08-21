package org.missions.objective.trigger.event.events;

import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.NotNull;
import org.missions.Category;
import org.missions.annotations.TypesGetter;
import org.missions.objective.trigger.event.criteria.EventCriteria;
import org.spongepowered.api.event.Event;

import java.util.Collection;

/**
 * A EventType is a category for all known events (typically paired with ActionEvent) within Sponge.
 * This EventType is designed to hold all criteria for the event
 *
 * @param <E> The Sponge event to listen to
 */
@TypesGetter(from = EventTypes.class)
public interface EventType<E extends Event> extends Category {

    /**
     * Gets a description of the event, essentially what the event will do
     *
     * @return A component type of the description
     */
    @NotNull Component getDescription();

    /**
     * Gets all compatible EventCriteria for the EventType
     *
     * @return A unmodifiable collection of all EventCriteria compatible with this Event
     */
    @NotNull Collection<EventCriteria> getPossibleCriteria();

    /**
     * Gets the Sponge event attached to this EventType
     *
     * @return The Sponge event this EventType should listen to
     */
    @NotNull Class<E> getListeningToEvent();
}
