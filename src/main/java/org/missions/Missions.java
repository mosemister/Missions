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

/**
 * The main class to boot the Sponge plugins
 */
@Plugin("mission")
public class Missions {

    private static Missions plugin;
    private PluginContainer container;

    /**
     * Runs when the plugin is being contructed, this should not be called unless you are Sponge
     *
     * @param event the event
     */
    @Listener
    public void onConstruct(ConstructPluginEvent event) {
        plugin = this;
        container = event.plugin();
    }

    /**
     * The place where all commands are registered, this should not be called unless you are Sponge
     *
     * @param event The event to register commands
     */
    @Listener
    public void registerCommands(RegisterCommandEvent<Command.Parameterized> event) {

    }

    /**
     * The place where the plugin will start, this should not be called unless you are Sponge
     *
     * @param event the starting server event
     */
    @Listener
    public void onStartingServer(StartingEngineEvent<Server> event) {

    }

    /*
    Don't plan on using this one, here if we need it
     */
    /*@Listener
    public void onStartingClient(StartingEngineEvent<Client> event){

    }*/

    /**
     * Gets this plugins container
     *
     * @return Gets the plugin container for Missions
     */
    public PluginContainer getContainer() {
        return this.container;
    }

    /**
     * Gets a category type object from its ID and known type
     *
     * @param from The class that the category type is from
     * @param id   The id of the object you are attempting to get
     * @param <T>  The type that the category type is from
     * @return The object of the category type if present, if not then {@link Optional#empty()}
     */
    public <T extends Category> Optional<T> getType(Class<T> from, String id) {
        return getTypes(from, (value) -> value.getId().equals(id)).findAny();
    }

    /**
     * Gets a set of all the category items from the specified type that also came from the specified plugin
     *
     * @param from      The class of the category
     * @param container The plugin container to filter by
     * @param <T>       The type of the category
     * @return A set of all known objects of the specified type
     */
    public <T extends Category> Set<T> getTypes(Class<T> from, PluginContainer container) {
        return getTypes(from, (value) -> value.getPlugin().equals(container)).collect(Collectors.toSet());
    }

    /**
     * Gets a set of all the category items from the specified type
     *
     * @param from The class of the category
     * @param <T>  the type of the category
     * @return A set of all known objects of the specified type
     */
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

    /**
     * Gets the Missions object used to start the plugin
     *
     * @return Gets the plugin main class
     */
    public static Missions getPlugin() {
        return plugin;
    }

}
