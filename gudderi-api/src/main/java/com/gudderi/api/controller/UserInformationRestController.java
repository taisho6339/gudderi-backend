package com.gudderi.api.controller;

import com.gudderi.api.controller.response.UserInformationResponse;
import com.gudderi.api.domain.UserInformation;
import com.gudderi.api.service.UserInformationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/information")
public class UserInformationRestController {

    private UserInformationService userInformationService;

    public UserInformationRestController(@Autowired UserInformationService userInformationService) {
        this.userInformationService = userInformationService;
    }

    @GetMapping(path = "/{userId}")
    public UserInformationResponse index(@PathVariable int userId, @PageableDefault(size = 30) Pageable pageable) {
        long totalCount = userInformationService.getTotalUserInformationCount(userId);
        if (totalCount <= 0) {
            return new UserInformationResponse(totalCount, Collections.emptyList());
        }
        List<UserInformation> userInformationList = userInformationService.getUserInformation(userId, pageable);
        return new UserInformationResponse(totalCount, userInformationList);
    }
}
