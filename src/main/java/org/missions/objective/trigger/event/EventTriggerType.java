package org.missions.objective.trigger.event;

import net.kyori.adventure.text.Component;
import org.missions.Missions;
import org.missions.objective.ObjectiveBuilder;
import org.missions.objective.trigger.TriggerType;
import org.spongepowered.plugin.PluginContainer;

public class EventTriggerType implements TriggerType {

    @Override
    public String getName() {
        return "EventTrigger";
    }

    @Override
    public PluginContainer getPlugin() {
        return Missions.getPlugin().getContainer();
    }

    @Override
    public Component getDescription() {
        return Component.text("Complete when the specified event occurs");
    }

    @Override
    public ObjectiveBuilder<?> createBuilder() {
        return new EventTriggerBuilder();
    }
}
