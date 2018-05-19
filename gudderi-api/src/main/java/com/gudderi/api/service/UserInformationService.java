package com.gudderi.api.service;

import com.gudderi.api.domain.UserInformation;
import com.gudderi.api.repository.UserInformationRepository;
import com.gudderi.core.annotation.GudderiTransactional;
import com.gudderi.core.component.message.MessageTemplate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserInformationService {

    private final UserInformationRepository userInformationRepository;
    private final MessageTemplate messageTemplate;

    @GudderiTransactional
    public void readInformation(int userId, int informationId) {
        userInformationRepository.updateReadFlag(userId, informationId);
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
