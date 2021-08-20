package org.missions.mission.props;

import org.missions.Category;
import org.missions.annotations.TypesGetter;

@TypesGetter(from = MissionPropKeys.class)
public interface MissionPropKey<S, V extends MissionPropValue<S>> extends Category {

    Class<V> getValueClass();

}
