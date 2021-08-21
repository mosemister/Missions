package org.missions;

import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

/**
 * This is a true singleton. Only when the {@link Singleton#get()} is called
 * will it attempt to get the value
 * (lowering the boot time as well as lowering resource usage if the value is never received)
 *
 * @param <T> The type expected to be received from {@link Singleton#get()}
 */
public class Singleton<T> implements Supplier<T> {

    private T value;
    private final Supplier<T> supplier;

    /**
     * The supplier for getting the value when required
     * @param supplier The supplier in question
     */
    public Singleton(Supplier<T> supplier) {
        this.supplier = supplier;
    }

    @Override
    public @NotNull T get() {
        if (this.value == null) {
            this.value = supplier.get();
        }
        return this.value;
    }
}
