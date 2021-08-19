package org.missions.objective.trigger.event;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.missions.mission.MissionBuilder;
import org.missions.objective.Objective;
import org.missions.objective.ObjectiveBuilder;
import org.missions.objective.ObjectiveTypes;
import org.missions.objective.trigger.event.events.ListeningEvent;
import org.spongepowered.configurate.ConfigurationNode;

public class EventTriggerObjectiveBuilder extends ObjectiveBuilder<EventTriggerType> {

    protected ListeningEvent<?, ?> event;

    public ListeningEvent<?, ?> getEvent() {
        return event;
    }

    public EventTriggerObjectiveBuilder setEvent(ListeningEvent<?, ?> event) {
        this.event = event;
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
