package org.missions.objective.trigger.event.criteria;

import org.jetbrains.annotations.NotNull;
import org.spongepowered.api.item.inventory.ItemStack;

public interface EventCriteriaMeta<EC extends EventCriteria> {

    @NotNull EC getCriteria();



}
