package org.missions;

import org.jetbrains.annotations.NotNull;
import org.spongepowered.plugin.PluginContainer;

/**
 * Anything that should have a ID, should implement Category
 */
public interface Category {

    @NotNull String getName();

    @NotNull PluginContainer getPlugin();

    default @NotNull String getId() {
        return getPlugin().metadata().id() + ":" + getName().toLowerCase().replace(" ", "_");
    }
}
