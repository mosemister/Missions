package org.missions.objective;

public abstract class ObjectiveBuilder<O extends ObjectiveType> {

    protected String name;
    protected Objective<?> parent;
    protected boolean optional;

    public abstract Objective<O> build();

    public abstract O getType();

    public String getName() {
        return name;
    }

    public ObjectiveBuilder<O> setName(String name) {
        this.name = name;
        return this;
    }

    public Objective<?> getParent() {
        return parent;
    }

    public ObjectiveBuilder<O> setParent(Objective<?> parent) {
        this.parent = parent;
        return this;
    }

    public boolean isOptional() {
        return optional;
    }

    public ObjectiveBuilder<O> setOptional(boolean optional) {
        this.optional = optional;
        return this;
    }
}
