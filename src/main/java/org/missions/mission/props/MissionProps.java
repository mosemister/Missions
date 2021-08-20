package org.missions.mission.props;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Gets the props that will be created/used within the mission
 */
public class MissionProps {

    private final List<MissionPropKeyValue<?, ?>> keyValues = new ArrayList<>();

    /**
     * Registers a new value to the props
     *
     * @param key   The key of the value
     * @param value The value
     * @param <S>   The expected true value type
     * @param <V>   The valueProp type
     */
    public <S, V extends MissionPropValue<S>> void register(MissionPropKey<S, V> key, V value) {
        this.keyValues.add(new MissionPropKeyValue<>(key, value));
    }

    /**
     * Gets all the prop values that belong to the specified key
     *
     * @param propKey The specified prop key
     * @param <S>     the expected value type of the PropValue
     * @param <V>     the PropValue type
     * @return A set of all MissionPropValues with the key specified
     */
    public <S, V extends MissionPropValue<S>> Set<V> getValues(MissionPropKey<S, V> propKey) {
        return getValues(propKey.getId());
    }

    /**
     * Gets all prop values that belong to the specified key
     * @param id The specified key
     * @param <S> the expected value type of the PropValue
     * @param <V> the PropValue type
     * @return A set of all MissionPropValues with the key specified
     */
    public <S, V extends MissionPropValue<S>> Set<V> getValues(String id) {
        return this
                .keyValues
                .parallelStream()
                .filter(keyValue -> keyValue.getKey().getId().equals(id))
                .map(keyValue -> (V) keyValue.getValue())
                .collect(Collectors.toSet());
    }

    /**
     * Gets all prop Keys and value pairs that have the attached prop value of the specified type
     * @param ofType The expected prop value of the PropValue
     * @param <S> The type of the expected prop value of the PropValue
     * @param <V> The PropValue
     * @return The set of KeyValue pairs
     */
    public <S, V extends MissionPropValue<S>> Set<MissionPropKeyValue<S, V>> getKeyValues(Class<S> ofType) {
        return this
                .keyValues
                .parallelStream()
                .filter(keyValues -> keyValues.getValue().getValueClass().isAssignableFrom(ofType))
                .map(keyValues -> (MissionPropKeyValue<S, V>) keyValues)
                .collect(Collectors.toSet());
    }

}
