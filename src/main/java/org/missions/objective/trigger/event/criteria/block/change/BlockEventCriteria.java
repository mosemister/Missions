package org.missions.objective.trigger.event.criteria.block.change;

import org.missions.exceptions.parse.ParseException;
import org.missions.objective.trigger.event.criteria.EventCriteria;
import org.missions.objective.trigger.event.criteria.EventCriteriaMeta;
import org.missions.objective.trigger.event.criteria.generic.LinkedCriteria;
import org.spongepowered.api.block.transaction.BlockTransaction;
import org.spongepowered.configurate.ConfigurationNode;
import org.spongepowered.configurate.serialize.SerializationException;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * This is the common interface for linked event criteria typically for processing {@link BlockTransaction}.
 */
public interface BlockEventCriteria extends LinkedCriteria {

    @Override
    default Collection<EventCriteria> getLinkedCriteria() {
        throw new RuntimeException("Not implemented");
    }

    @Override
    default void serialize(ConfigurationNode node, EventCriteriaMeta<?, ?> meta) throws ParseException, SerializationException, IllegalArgumentException {
        EventCriteria criteria = meta.getCriteria();
        String[] split = criteria.getId().split(Pattern.quote(":"));
        if (split.length != 2) {
            throw new ParseException(criteria.getId(), String[].class, "cannot split the id");
        }
        ConfigurationNode n = node.node(split[0], split[1]);
        criteria.serialize(n, meta);
    }

    @Override
    default EventCriteriaMeta<?, ?> deserialize(ConfigurationNode node) throws ParseException, SerializationException {
        Set<? extends ConfigurationNode> nodeSet = node
                .childrenList()
                .parallelStream()
                .flatMap(node1 -> node1.childrenList().stream())
                .filter(node1 -> node1.key() instanceof Object[])
                .filter(node1 -> ((Object[]) node1.key()).length >= 2)
                .collect(Collectors.toSet());
        for (ConfigurationNode configNode : nodeSet) {
            String[] array = (String[]) configNode.key();
            String id = array[array.length - 2] + ":" + array[array.length - 1];
            Optional<EventCriteria> opCriteria = this.getLinkedCriteria().parallelStream().filter(ec -> ec.getId().equals(id)).findAny();
            if (opCriteria.isEmpty()) {
                continue;
            }
            return opCriteria.get().deserialize(configNode);
        }
        throw new SerializationException(node, Object.class, "Cannot find linked criteria from id");
    }
}
