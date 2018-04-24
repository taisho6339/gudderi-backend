package com.gudderi.batch.repository;

import com.gudderi.batch.domain.Artist;
import com.gudderi.batch.domain.Live;
import com.gudderi.batch.domain.LiveSchedule;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

@Mapper
public interface ArtistLiveRepository {
    Integer selectArtistId(@Param("artistName") String artistName);

    void insertArtist(@Param("artist") Artist artist);

    Integer selectLiveId(@Param("artistId") int artistId, @Param("liveName") String liveName);

    void insertLive(@Param("artistId") Integer artistId, @Param("live") Live live);

    boolean existsLiveSchedule(@Param("liveId") Integer liveId, @Param("liveDate") Date liveDate);

    void insertLiveSchedule(@Param("liveId") Integer liveId, @Param("liveSchedule") LiveSchedule liveSchedule);
}
