package com.gudderi.gudderibatch.annotation;

import org.springframework.transaction.annotation.Transactional;

@Transactional(rollbackFor = Exception.class)
public @interface GudderiTransactional {
}
