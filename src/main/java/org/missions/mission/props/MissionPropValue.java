package org.missions.mission.props;

import java.util.Optional;
import java.util.function.Supplier;

/**
 * The Mission Prop Value, designed to get the prop value in a just-in-time way
 *
 * @param <P> The expected type of the value
 */
public interface MissionPropValue<P> extends Supplier<Optional<P>> {

    /**
     * Gets the classtype of the expected type of the value
     *
     * @return A classtype
     */
    Class<P> getValueClass();
}
