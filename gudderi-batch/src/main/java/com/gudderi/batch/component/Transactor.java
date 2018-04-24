package com.gudderi.batch.component;

import org.springframework.stereotype.Component;

@Component
public interface Transactor {
    void requiresNew(Runnable runnable);
}
