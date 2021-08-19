package org.missions.objective.trigger.event.criteria;

import org.jetbrains.annotations.NotNull;

public interface EventCriteriaMeta<EC extends EventCriteria, EO> {

    @NotNull EC getCriteria();

    boolean isCriteriaMet(EO eventObject);


}
