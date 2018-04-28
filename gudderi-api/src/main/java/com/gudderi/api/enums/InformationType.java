package com.gudderi.api.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum InformationType implements Convertible<Integer> {
    RECEIVE_REQUEST(0, "receiveRequest");
    private Integer code;
    private String templateName;
}
