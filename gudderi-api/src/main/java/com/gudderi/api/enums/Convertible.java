package com.gudderi.api.enums;

import java.util.Arrays;

public interface Convertible<T> {
    /**
     * コード値を返します。
     *
     * @return コード値
     */
    T getCode();

    static <E extends Enum<E> & Convertible, T> E findByCode(Class<E> clazz, T code) {
        return Arrays.stream(clazz.getEnumConstants())
                .filter(e -> e.getCode().equals(code))
                .findAny()
                .orElse(null);
    }
}
