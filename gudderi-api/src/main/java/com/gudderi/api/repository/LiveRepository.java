package com.gudderi.api.repository;

import com.gudderi.api.domain.Live;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Mapper
public interface LiveRepository {
    List<Live> selectLiveList(
            @Param("artistId") int artistId,
            @Param("pageable") Pageable pageable
    );

    long countLiveList(
            @Param("artistId") int artistId
    );

    void insertParticipantUser(
            @Param("userId") int userId,
            @Param("liveScheduleId") int liveScheduleId
    );

    boolean existLiveParticipant(
            @Param("userId") int userId,
            @Param("liveScheduleId") int liveScheduleId
    );
}
