package com.gudderi.core.component.context;

public class ExecContextHolder {

    private ExecContextHolder() {
    }

    private static HolderStrategy holderStrategy = new ThreadLocalHolderStrategy();

    public static void setStrategy(HolderStrategy strategy) {
        holderStrategy = strategy;
    }

    public static void clear() {
        holderStrategy.clear();
    }

    public static ExecContext get() {
        return holderStrategy.get();
    }

    public static void set(ExecContext ctx) {
        holderStrategy.set(ctx);
    }
}