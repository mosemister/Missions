package org.missions.objective;

import net.kyori.adventure.text.Component;
import org.missions.Category;
import org.missions.annotations.TypesGetter;

@TypesGetter(from = ObjectiveTypes.class)
public interface ObjectiveType extends Category {

    Component getDescription();

    ObjectiveBuilder<?> createBuilder();

}
