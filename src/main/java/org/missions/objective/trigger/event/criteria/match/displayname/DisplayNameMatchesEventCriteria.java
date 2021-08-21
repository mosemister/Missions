package org.missions.objective.trigger.event.criteria.match.displayname;

import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.missions.Missions;
import org.missions.objective.trigger.event.criteria.EventCriteria;
import org.missions.objective.trigger.event.criteria.EventCriteriaMeta;
import org.spongepowered.configurate.ConfigurationNode;
import org.spongepowered.configurate.serialize.SerializationException;
import org.spongepowered.plugin.PluginContainer;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

/**
 * This is the event criteria for if a display name matches to a specified string.
 */
public class DisplayNameMatchesEventCriteria implements EventCriteria {

    private final Object[] NODE_DISPLAY_NAME = {"DisplayName"};
    private final Object[] NODE_IGNORE_CASE = {"IgnoreCase"};

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

    @Override
    public void serialize(ConfigurationNode node, EventCriteriaMeta<?, ?> meta) throws SerializationException, IllegalArgumentException {
        if (!(meta instanceof DisplayNameMatchesEventMeta eventMeta)) {
            throw new IllegalArgumentException("Meta must be " + DisplayNameMatchesEventMeta.class.getSimpleName());
        }
        node.node(NODE_DISPLAY_NAME).set(eventMeta.getNameToMatch());
        node.node(NODE_IGNORE_CASE).set(eventMeta.isIgnoringCase());
    }

    @Override
    public EventCriteriaMeta<?, ?> deserialize(ConfigurationNode node) throws SerializationException {
        @Nullable String nameCheck = node.node(NODE_DISPLAY_NAME).getString();
        boolean ignoreCase = node.node(NODE_IGNORE_CASE).getBoolean();
        if (nameCheck == null) {
            throw new SerializationException("Cannot read string at " + Arrays.stream(NODE_DISPLAY_NAME).map(Object::toString).collect(Collectors.joining(".")));
        }
        return new DisplayNameMatchesEventMeta().setNameToMatch(nameCheck).setIgnoringCase(ignoreCase);
    }
}
