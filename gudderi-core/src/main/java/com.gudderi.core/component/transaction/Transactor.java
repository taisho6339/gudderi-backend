package com.gudderi.core.component.transaction;

import org.springframework.stereotype.Component;

@Component
public interface Transactor {
    void requiresNew(Runnable runnable);
}
