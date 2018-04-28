package com.gudderi.api.service;

import com.gudderi.api.domain.UserInformation;
import com.gudderi.api.repository.UserInformationRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserInformationService {

    private UserInformationRepository userInformationRepository;

    public UserInformationService(@Autowired UserInformationRepository userInformationRepository) {
        this.userInformationRepository = userInformationRepository;
    }

    public List<UserInformation> getUserInformation(int userId) {
        List<UserInformation> userInformationList = userInformationRepository.selectUserInformation(userId);
        return userInformationList;
    }
}
