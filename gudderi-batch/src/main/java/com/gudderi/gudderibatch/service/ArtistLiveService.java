package com.gudderi.gudderibatch.service;

import com.gudderi.gudderibatch.component.Transactor;
import com.gudderi.gudderibatch.domain.Artist;
import com.gudderi.gudderibatch.domain.Live;
import com.gudderi.gudderibatch.domain.LiveSchedule;
import com.gudderi.gudderibatch.enums.Prefecture;
import com.gudderi.gudderibatch.repository.ArtistLiveExtractRepository;
import com.gudderi.gudderibatch.repository.ArtistLiveRepository;

import org.eclipse.collections.impl.factory.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ArtistLiveService {

    private ArtistLiveExtractRepository artistLiveExtractRepository;
    private ArtistLiveRepository artistLiveRepository;
    private Transactor transactor;

    public ArtistLiveService(
            @Autowired ArtistLiveExtractRepository artistLiveExtractRepository,
            @Autowired ArtistLiveRepository artistLiveRepository,
            @Autowired Transactor transactor
    ) {
        this.artistLiveExtractRepository = artistLiveExtractRepository;
        this.artistLiveRepository = artistLiveRepository;
        this.transactor = transactor;
    }

    /**
     * アーティストの情報も頻繁に変わるので毎回作り直す
     */
    public void setUpArtistLiveData() {
//        List<Artist> artists = artistLiveExtractRepository.getArtistLiveList();
        LiveSchedule liveSchedule = LiveSchedule.builder()
                .liveDate(new Date(0))
                .livePlace("高円寺")
                .livePrefecture(Prefecture.TOKYO)
                .build();
        Live live = Live.builder()
                .liveName("ダミーライブ")
                .liveScheduleList(Lists.mutable.of(liveSchedule))
                .build();
        Artist artist = Artist.builder()
                .artistName("ななさ")
                .liveList(Lists.mutable.of(live))
                .build();
        List<Artist> artists = Lists.mutable.of(artist);
        artists.forEach(a -> {
            transactor.requiresNew(() -> commitArtistTransaction(a));
        });
    }

    // 1トランザクションでinsertを大量に発行しているので、
    // パフォーマンスが考慮しなければならない分量になったら要チューニング
    private void commitArtistTransaction(Artist artist) {
        int artistId = insertArtistIfNeeded(artist);
        artist.getLiveList().forEach(live -> {
            int liveId = insertLiveIfNeeded(artistId, live);
            live.getLiveScheduleList().forEach(liveSchedule -> {
                insertLiveScheduleIfNeeded(liveId, liveSchedule);
            });
        });
    }

    private Integer insertArtistIfNeeded(Artist artist) {
        Integer artistId = artistLiveRepository.selectArtistId(artist.getArtistName());
        if (artistId == null) {
            artistLiveRepository.insertArtist(artist);
            artistId = artist.getArtistId();
        }
        return artistId;
    }

    private Integer insertLiveIfNeeded(int artistId, Live live) {
        Integer liveId = artistLiveRepository.selectLiveId(live.getLiveName());
        if (liveId == null) {
            artistLiveRepository.insertLive(artistId, live);
            liveId = live.getLiveId();
        }
        return liveId;
    }

    private void insertLiveScheduleIfNeeded(int liveId, LiveSchedule liveSchedule) {
        boolean exists = artistLiveRepository.existsLiveSchedule(liveId, liveSchedule.getLiveDate());
        if (exists) {
            return;
        }
        artistLiveRepository.insertLiveSchedule(liveId, liveSchedule);
    }
}
