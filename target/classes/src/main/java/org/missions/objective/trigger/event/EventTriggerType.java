package org.missions.objective.trigger.event;

import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.NotNull;
import org.missions.Missions;
import org.missions.exceptions.parse.ParseException;
import org.missions.mission.MissionBuilder;
import org.missions.objective.Objective;
import org.missions.objective.ObjectiveBuilder;
import org.missions.objective.trigger.TriggerType;
import org.spongepowered.configurate.ConfigurationNode;
import org.spongepowered.configurate.serialize.SerializationException;
import org.spongepowered.plugin.PluginContainer;

public class EventTriggerType implements TriggerType {

    @Override
    public @NotNull String getName() {
        return "EventTrigger";
    }

    @Override
    public @NotNull PluginContainer getPlugin() {
        return Missions.getPlugin().getContainer();
    }

    @Override
    public @NotNull Component getDescription() {
        return Component.text("Complete when the specified event occurs");
    }

    @Override
    public @NotNull ObjectiveBuilder<?> createBuilder() {
        return new EventTriggerObjectiveBuilder();
    }

    @Override
    public void serialize(@NotNull ConfigurationNode node, @NotNull Objective<?> objective) throws SerializationException, ParseException {
        //TODO
        throw new RuntimeException("Not Implemented");
    }

    @Override
    public @NotNull Objective<?> deserialize(@NotNull ConfigurationNode node, @NotNull MissionBuilder builder) throws SerializationException, ParseException {
        throw new RuntimeException("Not Implemented");
    }
}
