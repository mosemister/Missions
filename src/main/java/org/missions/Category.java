package org.missions;

import org.jetbrains.annotations.NotNull;
import org.spongepowered.plugin.PluginContainer;

/**
 * Anything that should have a ID, should implement Category
 */
public interface Category {

    /**
     * Gets the display name of the category item
     *
     * @return Gets the display name of the category item
     */
    @NotNull String getName();

    /**
     * Gets where the category item came from
     *
     * @return Gets the plugin that the category item came from
     */
    @NotNull PluginContainer getPlugin();

    /**
     * Gets the ID of the category item, this is in the same format that Minecraft's
     * ResourceKey comes in (Plugin:IdName)
     * <p>
     * The ID name must following the following rules:
     * - Must be in lowercase
     * - Cannot contain spaces
     *
     * @return The ID of the category item
     */
    default @NotNull String getId() {
        return getPlugin().metadata().id() + ":" + getName().toLowerCase().replace(" ", "_");
    }
}
