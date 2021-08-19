package org.missions.objective.trigger.event.criteria.match.displayname;

import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.NotNull;
import org.missions.Missions;
import org.missions.objective.trigger.event.criteria.EventCriteria;
import org.spongepowered.plugin.PluginContainer;

import java.util.Collection;
import java.util.Collections;

/**
 * This is the event criteria for if a display name matches to a specified string.
 */
public class DisplayNameMatchesEventCriteria implements EventCriteria {
    @Override
    public @NotNull String getName() {
        return "ItemNameMatches";
    }

    @Override
    public @NotNull PluginContainer getPlugin() {
        return Missions.getPlugin().getContainer();
    }

    @Override
    public @NotNull Component getDescription() {
        return Component.text("Where the item's name matches the specified name");
    }

    @Override
    public Collection<DisplayNameMatchesEventMeta> createMeta() {
        return Collections.singleton(new DisplayNameMatchesEventMeta());
    }
}
