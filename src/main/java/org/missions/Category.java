package org.missions;

import org.spongepowered.plugin.PluginContainer;

public interface Category {

    String getName();

    PluginContainer getPlugin();

    default String getId() {
        return getPlugin().metadata().id() + ":" + getName().toLowerCase().replace(" ", "_");
    }
}
