package org.missions.mission.props;

import org.missions.Category;
import org.missions.annotations.TypesGetter;

/**
 * The key that provides a simple way of extracting data from the MissionProps
 *
 * @param <S> The expected propValue result type
 * @param <V> The expected propValue type
 */
@TypesGetter(from = MissionPropKeys.class)
public interface MissionPropKey<S, V extends MissionPropValue<S>> extends Category {

    /**
     * The type of the expected propValue type
     *
     * @return The type
     */
    Class<V> getValueClass();

}
