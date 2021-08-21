package org.missions.objective.trigger.event.criteria.block.change;

import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.NotNull;
import org.missions.Missions;
import org.spongepowered.api.block.transaction.BlockTransaction;
import org.spongepowered.plugin.PluginContainer;

/**
 * This is the linked event criteria typically for processing {@link BlockTransaction#original()}.
 */
public class PreviousBlockEventCriteria implements BlockEventCriteria {
    @Override
    public @NotNull String getName() {
        return "previous block";
    }

    @Override
    public @NotNull PluginContainer getPlugin() {
        return Missions.getPlugin().getContainer();
    }

    @Override
    public @NotNull Component getDescription() {
        return Component.text("The previous block information");
    }
}
