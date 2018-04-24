package com.gudderi.batch.annotation;

import org.springframework.transaction.annotation.Transactional;

@Transactional(rollbackFor = Exception.class)
public @interface GudderiTransactional {
}
