package com.gudderi.api.repository;

import com.gudderi.api.domain.UserInformation;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserInformationRepository {
    List<UserInformation> selectUserInformation(@Param("userId") Integer userId);
}
