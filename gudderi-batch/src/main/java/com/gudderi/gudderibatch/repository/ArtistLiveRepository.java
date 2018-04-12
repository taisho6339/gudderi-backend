package com.gudderi.gudderibatch.repository;

import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * グループとそれに紐づくライブ情報を取得するRepository
 */
@Repository
public interface ArtistLiveRepository {

    public List<Artist> getArtistLiveList();

    @AllArgsConstructor
    @Data
    class Artist {
        private String artistName;
        private List<Live> liveList;
    }

    @AllArgsConstructor
    @Data
    class Live {
        private String liveName;
        private List<LiveDate> liveDateList;
    }

    @AllArgsConstructor
    @Data
    class LiveDate {
        private String livePrefecture;
        private String livePlace;
        private Date liveDate;
    }
}
