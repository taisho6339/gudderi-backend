package com.gudderi.api.controller;

import com.gudderi.api.domain.UserInformation;
import com.gudderi.api.service.UserInformationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/information")
public class UserInformationRestController {

    private UserInformationService userInformationService;

    public UserInformationRestController(@Autowired UserInformationService userInformationService) {
        this.userInformationService = userInformationService;
    }

    @GetMapping(path = "/{userId}")
    public String index(@PathVariable int userId) {
        List<UserInformation> userInformationList = userInformationService.getUserInformation(userId);
        userInformationList.forEach(info -> {
            System.out.println(info);
        });
        return "OK";
    }
}
