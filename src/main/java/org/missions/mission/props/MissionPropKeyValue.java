package org.missions.mission.props;

import org.jetbrains.annotations.NotNull;

import java.util.Map;

public class MissionPropKeyValue<S, V extends MissionPropValue<S>> implements Map.Entry<MissionPropKey<S, V>, V> {

    private final @NotNull MissionPropKey<S, V> key;
    private final @NotNull V value;

    public MissionPropKeyValue(@NotNull MissionPropKey<S, V> key, @NotNull V value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public @NotNull MissionPropKey<S, V> getKey() {
        return this.key;
    }

    @Override
    public @NotNull V getValue() {
        return this.value;
    }

    @Override
    public V setValue(V value) {
        throw new RuntimeException("Setting this value is not allowed");
    }
}
