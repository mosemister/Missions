package org.missions.annotations;

import org.jetbrains.annotations.NotNull;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Used to link a class to another class acting as a inferred enum
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface TypesGetter {

    /**
     * The class which is acting as a inferred enum is you from
     *
     * @return The inferred enum
     */
    @NotNull Class<?> from();
}
