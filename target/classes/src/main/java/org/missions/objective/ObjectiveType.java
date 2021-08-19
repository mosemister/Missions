package org.missions.objective;

import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.NotNull;
import org.missions.Category;
import org.missions.annotations.TypesGetter;
import org.missions.exceptions.parse.ParseException;
import org.missions.mission.MissionBuilder;
import org.spongepowered.configurate.ConfigurationNode;
import org.spongepowered.configurate.serialize.SerializationException;

/**
 * A objective type is a type of node that is within the mission,
 * this could be a action (example: where something get spawned in),
 * or a trigger (example: something needs to occur), etc.
 * <p>
 * Each objective type should be able to save and load its own objectives
 * as well as supply a builder to which a objective can be built.
 * <p>
 * Each objective type should be registered within the {@link ObjectiveTypes} class
 */
@TypesGetter(from = ObjectiveTypes.class)
public interface ObjectiveType extends Category {

    /**
     * The description of the objective, this should describe
     * what the trigger does in a general sense
     *
     * @return A component form of the description
     */
    @NotNull Component getDescription();

    /**
     * This will create a new objective builder for the
     * objective that this type is linked to
     *
     * @return A objective builder to build objectives of this type
     */
    @NotNull ObjectiveBuilder<?> createBuilder();

    /**
     * The ability to save a objective of this type
     *
     * @param node      The node that you wish to save to
     * @param objective The objective you wish to save
     * @throws IllegalArgumentException If a unsupported objective is supplied
     */
    void serialize(@NotNull ConfigurationNode node, @NotNull Objective<?> objective) throws SerializationException, ParseException, IllegalArgumentException;

    /**
     * The ability to load a objective of this type
     *
     * @param node The node that you wish to load from
     * @return A objective of this type with all values accounted for
     */
    @NotNull Objective<?> deserialize(@NotNull ConfigurationNode node, @NotNull MissionBuilder builder) throws SerializationException, ParseException;

}
