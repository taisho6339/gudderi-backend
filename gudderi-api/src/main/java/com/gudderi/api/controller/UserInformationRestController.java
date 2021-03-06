package com.gudderi.api.controller;

import com.gudderi.api.controller.response.UserInformationResponse;
import com.gudderi.api.domain.UserInformation;
import com.gudderi.api.service.UserInformationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "/api/information", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class UserInformationRestController {

    private final UserInformationService userInformationService;

    @GetMapping(path = "/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public UserInformationResponse index(@PathVariable int userId, @PageableDefault(size = 30) Pageable pageable) {
        //TODO: 認証失敗時エラー
        long totalCount = userInformationService.getTotalUserInformationCount(userId);
        if (totalCount <= 0) {
            return new UserInformationResponse(totalCount, Collections.emptyList());
        }
        List<UserInformation> userInformationList = userInformationService.getUserInformation(userId, pageable);
        return new UserInformationResponse(totalCount, userInformationList);
    }

    @PutMapping(path = "/{userId}/{informationId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void readInformation(@PathVariable int userId, @PathVariable int informationId) {
        userInformationService.readInformation(userId, informationId);
    }
}
