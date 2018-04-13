package com.gudderi.gudderibatch.component;

import org.springframework.stereotype.Component;

@Component
public interface Transactor {
    void requiresNew(Runnable runnable);
}
