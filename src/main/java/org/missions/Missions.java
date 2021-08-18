package org.missions;

import org.missions.annotations.TypesGetter;
import org.spongepowered.api.Server;
import org.spongepowered.api.command.Command;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.lifecycle.ConstructPluginEvent;
import org.spongepowered.api.event.lifecycle.RegisterCommandEvent;
import org.spongepowered.api.event.lifecycle.StartingEngineEvent;
import org.spongepowered.plugin.PluginContainer;
import org.spongepowered.plugin.jvm.Plugin;

import java.lang.reflect.Field;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Plugin("mission")
public class Missions {

    private static Missions plugin;
    private PluginContainer container;

    @Listener
    public void onConstruct(ConstructPluginEvent event) {
        plugin = this;
        container = event.plugin();
    }

    @Listener
    public void registerCommands(RegisterCommandEvent<Command.Parameterized> event) {

    }

    @Listener
    public void onStartingServer(StartingEngineEvent<Server> event) {

    }

    /*
    Don't plan on using this one, here if we need it
     */
    /*@Listener
    public void onStartingClient(StartingEngineEvent<Client> event){

    }*/

    public PluginContainer getContainer() {
        return this.container;
    }

    public <T extends Category> Optional<T> getType(Class<T> from, String id) {
        return getTypes(from, (value) -> value.getId().equals(id)).findAny();
    }

    public <T extends Category> Set<T> getTypes(Class<T> from, PluginContainer container) {
        return getTypes(from, (value) -> value.getPlugin().equals(container)).collect(Collectors.toSet());
    }

    public <T extends Category> Set<T> getTypes(Class<T> from) {
        return getTypes(from, (value) -> true).collect(Collectors.toSet());
    }

    private <T extends Category> Stream<T> getTypes(Class<T> from, Predicate<T> filter) {
        TypesGetter getter = from.getAnnotation(TypesGetter.class);
        if (getter == null) {
            throw new IllegalArgumentException(from.getSimpleName() + " does not specify a TypeGetter annotation");
        }
        Field[] fields = getter.from().getDeclaredFields();
        if (fields.length == 0) {
            throw new IllegalArgumentException(getter.from().getSimpleName() + " does not specify any fields");
        }
        return Stream.of(fields)
                .filter(field -> field.getType().isAssignableFrom(from))
                .map(field -> {
                    try {
                        return (T) field.get(null);
                    } catch (IllegalAccessException e) {
                        return null;
                    }
                })
                .filter(Objects::nonNull)
                .filter(filter);

    }

    public static Missions getPlugin() {
        return plugin;
    }

}
