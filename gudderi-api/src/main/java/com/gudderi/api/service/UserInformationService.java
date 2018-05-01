package com.gudderi.api.service;

import com.gudderi.api.domain.UserInformation;
import com.gudderi.api.repository.UserInformationRepository;
import com.gudderi.core.component.message.MessageTemplate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserInformationService {

    private UserInformationRepository userInformationRepository;
    private MessageTemplate messageTemplate;

    public UserInformationService(
            @Autowired UserInformationRepository userInformationRepository,
            @Autowired MessageTemplate messageTemplate
    ) {
        this.userInformationRepository = userInformationRepository;
        this.messageTemplate = messageTemplate;
    }

    public long getTotalUserInformationCount(int userId) {
        return userInformationRepository.countUserInformation(userId);
    }

    public List<UserInformation> getUserInformation(int userId, Pageable pageable) {
        List<UserInformationRepository.UserInformation> userInformationList
                = userInformationRepository.selectUserInformation(userId, pageable);
        return userInformationList.stream()
                .map(info -> new UserInformation(
                        info,
                        messageTemplate.applyTemplate(info.getInformationType(), info.getTemplateParameters())
                )).collect(Collectors.toList());
    }
}
