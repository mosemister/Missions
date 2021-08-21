package org.missions.objective.trigger.event.criteria;

import org.jetbrains.annotations.NotNull;

/**
 * The EventCriteriaMeta is meta information for the specified criteria,
 * this is used in the {@link org.missions.mission.running.RunningMission}
 * to see if the meta has been completed within the mission
 *
 * @param <EC> The Event Criteria
 * @param <EO> The Object to compare
 */
public interface EventCriteriaMeta<EC extends EventCriteria, EO> {

    /**
     * Gets the attached criteria object
     *
     * @return The attached event criteria
     */
    @NotNull EC getCriteria();

    /**
     * Checks if the criteria is met
     *
     * @param eventObject the object to compare to
     * @return if the criteria has been met
     */
    boolean isCriteriaMet(EO eventObject);


}
