package com.gudderi.gudderibatch.repository;

import com.gudderi.gudderibatch.domain.Artist;
import com.gudderi.gudderibatch.domain.Live;
import com.gudderi.gudderibatch.domain.LiveSchedule;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ArtistLiveRepository {
    int insertArtist(@Param("artist") Artist artist);

    int insertLive(@Param("artistId") Integer artistId, @Param("live") Live live);

    void insertLiveSchedule(@Param("liveId") Integer liveId, @Param("liveSchedule") LiveSchedule liveSchedule);
}
