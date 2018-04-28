package com.gudderi.api.domain;

import com.gudderi.api.enums.InformationType;

import java.util.Date;

import lombok.Data;

@Data
public class UserInformation {
    private int userInformationId;
    private InformationType informationType;
    private boolean readFlag;
    private Date createdAt;
}
