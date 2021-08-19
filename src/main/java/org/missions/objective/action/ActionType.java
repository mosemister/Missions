package org.missions.objective.action;

import org.missions.objective.ObjectiveType;

/**
 * An ActionType is something that will occur if the parent is complete.
 * This could be placing a block or entity into the world, or sending the
 * player to another location, etc.
 */
public interface ActionType extends ObjectiveType {
}
