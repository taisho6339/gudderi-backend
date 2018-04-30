package com.gudderi.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum InformationType implements Convertible<Integer> {
    RECEIVE_REQUEST(0, "依頼申請を受信しました!", "receiveRequest");
    private Integer code;
    private String title;
    private String templateName;
}
