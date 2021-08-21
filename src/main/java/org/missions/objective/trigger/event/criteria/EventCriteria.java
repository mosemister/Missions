package org.missions.objective.trigger.event.criteria;

import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.NotNull;
import org.missions.Category;
import org.missions.annotations.TypesGetter;
import org.missions.exceptions.parse.ParseException;
import org.spongepowered.configurate.ConfigurationNode;
import org.spongepowered.configurate.serialize.SerializationException;

import java.util.Collection;
import java.util.Optional;

/**
 * EventCriteria is a check that occurs with a event, this can be used to make sure that the event fired
 * is the exact event, or if its just a event that you dont want but fired
 */
@TypesGetter(from = EventsCriteria.class)
public interface EventCriteria extends Category {

    /**
     * gets the description of the event criteria, explaining what the critera is
     *
     * @return The description in Component
     */
    @NotNull Component getDescription();

    /**
     * Create a new meta objects of the event criteria
     *
     * @return A unmodifiable collection of blank meta
     */
    Collection<? extends EventCriteriaMeta<?, ?>> createMeta();

    /**
     * serialize meta into the provided configNode. The meta must be supported by the event criteria, throws IllegalArgumentException if its not
     *
     * @param node node to save to
     * @param meta the meta to save.
     * @throws ParseException           Thrown if the provided meta cannot be converted into serializable objects
     * @throws SerializationException   thrown if the value cannot be serialized
     * @throws IllegalArgumentException thrown if the meta is not of the correct type
     */
    void serialize(ConfigurationNode node, EventCriteriaMeta<?, ?> meta) throws ParseException, SerializationException, IllegalArgumentException;

    /**
     * deserialize meta from the provided configNode.
     *
     * @param node node to load from
     * @return The meta
     * @throws ParseException         thrown if a value cannot be converted into meta
     * @throws SerializationException thrown if getting a value fails
     */
    EventCriteriaMeta<?, ?> deserialize(ConfigurationNode node) throws ParseException, SerializationException;

    /**
     * Create a new meta object of the specified event criteria
     *
     * @param clazz The EventCriteriaMeta class type
     * @param <T>   The Type of the eventCriteriaMeta
     * @return the new meta object, {@link Optional#empty()} if the meta is not supported
     */
    default <T extends EventCriteriaMeta<?, ?>> Optional<T> createMeta(Class<T> clazz) {
        return createMeta().parallelStream().filter(clazz::isInstance).findAny().map(meta -> (T) meta);
    }

}
