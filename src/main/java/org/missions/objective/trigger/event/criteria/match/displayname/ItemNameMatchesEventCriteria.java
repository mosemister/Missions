package org.missions.objective.trigger.event.criteria.match.displayname;

import net.kyori.adventure.text.Component;
import org.missions.Missions;
import org.missions.objective.trigger.event.criteria.EventCriteria;
import org.spongepowered.plugin.PluginContainer;

public class ItemNameMatchesEventCriteria implements EventCriteria {
    @Override
    public String getName() {
        return "ItemNameMatches";
    }

    @Override
    public PluginContainer getPlugin() {
        return Missions.getPlugin().getContainer();
    }

    @Override
    public Component getDescription() {
        return Component.text("Where the item's name matches the specified name");
    }
}
