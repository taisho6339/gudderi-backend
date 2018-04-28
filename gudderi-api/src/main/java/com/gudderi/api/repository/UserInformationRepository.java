package com.gudderi.api.repository;

import com.gudderi.api.enums.InformationType;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Mapper
public interface UserInformationRepository {
    List<UserInformation> selectUserInformation(
            @Param("userId") Integer userId,
            @Param("pageable") Pageable pageable
    );

    @Data
    public class UserInformation {
        private int userInformationId;
        private InformationType informationType;
        private String templateParameters;
        private boolean readFlag;
        private Date createdAt;
    }
}
