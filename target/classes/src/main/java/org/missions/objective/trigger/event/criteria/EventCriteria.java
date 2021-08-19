package org.missions.objective.trigger.event.criteria;

import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.NotNull;
import org.missions.Category;
import org.missions.annotations.TypesGetter;

import java.util.Collection;
import java.util.Optional;

@TypesGetter(from = EventsCriteria.class)
public interface EventCriteria extends Category {

    @NotNull Component getDescription();

    Collection<? extends EventCriteriaMeta<?, ?>> createMeta();

    default <T extends EventCriteriaMeta<?, ?>> Optional<T> createMeta(Class<T> clazz) {
        return createMeta().parallelStream().filter(clazz::isInstance).findAny().map(meta -> (T) meta);
    }

}
