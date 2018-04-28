package com.gudderi.api.domain;

import com.gudderi.api.repository.UserInformationRepository;

import java.util.Date;

import lombok.Getter;

@Getter
public class UserInformation {
    private int userInformationId;
    private String title;
    private String message;
    private boolean readFlag;
    private Date createdAt;

    public UserInformation(UserInformationRepository.UserInformation userInformation, String message) {
        this.userInformationId = userInformation.getUserInformationId();
        this.title = userInformation.getInformationType().getTitle();
        this.message = message;
        this.readFlag = userInformation.isReadFlag();
        this.createdAt = userInformation.getCreatedAt();
    }
}
