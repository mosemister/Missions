package org.missions.objective.trigger.event.events.block;

import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.NotNull;
import org.missions.Missions;
import org.missions.objective.trigger.event.criteria.EventCriteria;
import org.missions.objective.trigger.event.events.EventType;
import org.spongepowered.api.event.block.ChangeBlockEvent;
import org.spongepowered.plugin.PluginContainer;

import java.util.Collection;

/**
 * The event type for BlockChange events
 */
public class BlockChangeEventType implements EventType<ChangeBlockEvent.Post> {
    @Override
    public @NotNull String getName() {
        return "Change Block Event";
    }

    @Override
    public @NotNull PluginContainer getPlugin() {
        return Missions.getPlugin().getContainer();
    }

    @Override
    public @NotNull Component getDescription() {
        return Component.text("When a block changes to/from another block");
    }

    @Override
    public @NotNull Collection<EventCriteria> getPossibleCriteria() {
        throw new RuntimeException("Not Implemented yet");
    }

    @Override
    public @NotNull Class<ChangeBlockEvent.Post> getListeningToEvent() {
        return ChangeBlockEvent.Post.class;
    }
}
