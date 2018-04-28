package com.gudderi.api.controller.response;

import com.gudderi.api.domain.UserInformation;

import java.util.List;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UserInformationResponse {
    public List<UserInformation> userInformation;
}
