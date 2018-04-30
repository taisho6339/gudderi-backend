package com.gudderi.core.component.context;

/**
 * ThreadLocalを利用した実行コンテキストの維持戦略実装クラス。
 */
public class ThreadLocalHolderStrategy implements HolderStrategy {

    private static final ThreadLocal<ExecContext> TL = new ThreadLocal<>();

    @Override
    public void clear() {
        TL.remove();
    }

    @Override
    public ExecContext get() {
        ExecContext ctx = TL.get();
        if (ctx == null) {
            ctx = createEmptyContext();
            TL.set(ctx);
        }
        return ctx;
    }

    @Override
    public void set(ExecContext ctx) {
        if (ctx != null) {
            TL.set(ctx);
        }
    }

    @Override
    public ExecContext createEmptyContext() {
        return new ExecContext();
    }

}