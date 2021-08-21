package org.missions.objective.trigger.event;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.missions.mission.MissionBuilder;
import org.missions.objective.Objective;
import org.missions.objective.ObjectiveBuilder;
import org.missions.objective.ObjectiveTypes;
import org.missions.objective.trigger.event.criteria.EventCriteriaMeta;
import org.missions.objective.trigger.event.events.EventType;
import org.spongepowered.configurate.ConfigurationNode;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * The builder class for {@link EventTriggerType}
 */
public class EventTriggerObjectiveBuilder extends ObjectiveBuilder<EventTriggerType> {

    private EventType<?> eventType;
    private final Set<EventCriteriaMeta<?, ?>> criteria = new HashSet<>();

    /**
     * Gets the eventType, is it null if not set
     *
     * @return The event type to use
     */
    public @Nullable EventType<?> getEvent() {
        return eventType;
    }

    /**
     * Sets the eventType to listen for
     *
     * @param event The provided event
     * @return this builder, for chaining
     */
    public EventTriggerObjectiveBuilder setEvent(@NotNull EventType<?> event) {
        this.eventType = event;
        return this;
    }

    /**
     * Gets the event criteria that must be met on event trigger
     *
     * @return a set of the criteria
     */
    public @NotNull Set<EventCriteriaMeta<?, ?>> getCriteria() {
        return this.criteria;
    }

    /**
     * Do not use
     *
     * @return nothing, always throws exception
     * @throws RuntimeException Do not use
     * @deprecated adding no criteria makes no sense
     */
    @Deprecated
    public EventTriggerObjectiveBuilder addCriteria() {
        throw new RuntimeException("Criteria must be specified");
    }

    /**
     * Adds the criteria in the builder
     *
     * @param meta The meta to add
     * @return this builder, for chaining
     */
    public EventTriggerObjectiveBuilder addCriteria(EventCriteriaMeta<?, ?>... meta) {
        return this.addCriteria(Arrays.asList(meta));
    }

    /**
     * Adds the criteria in the builder
     *
     * @param meta The meta to add
     * @return this builder, for chaining
     */
    public EventTriggerObjectiveBuilder addCriteria(Collection<EventCriteriaMeta<?, ?>> meta) {
        this.criteria.addAll(meta);
        return this;
    }

    @Override
    public @NotNull EventTriggerObjective<?, ?> build() {
        return new EventTriggerObjective<>(this);
    }

    @Override
    public @NotNull EventTriggerType getType() {
        return ObjectiveTypes.EVENT_TRIGGER;
    }

    @Override
    public EventTriggerObjectiveBuilder setName(@NotNull String name) {
        return (EventTriggerObjectiveBuilder) super.setName(name);
    }

    @Override
    public EventTriggerObjectiveBuilder setParent(@Nullable Objective<?> parent) {
        return (EventTriggerObjectiveBuilder) super.setParent(parent);
    }

    @Override
    public EventTriggerObjectiveBuilder fromNode(@NotNull ConfigurationNode node, @NotNull MissionBuilder builder) {
        return (EventTriggerObjectiveBuilder) super.fromNode(node, builder);
    }
}
