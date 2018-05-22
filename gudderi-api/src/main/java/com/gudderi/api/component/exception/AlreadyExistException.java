package com.gudderi.api.component.exception;

/**
 * すでに登録済みのデータを登録しようとしたときの例外
 */
public class AlreadyExistException extends Exception {
    public AlreadyExistException(String message) {
        super(message);
    }
}
