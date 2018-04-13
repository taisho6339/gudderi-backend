package com.gudderi.gudderibatch.service;

import com.gudderi.gudderibatch.component.Transactor;
import com.gudderi.gudderibatch.domain.Artist;
import com.gudderi.gudderibatch.domain.Live;
import com.gudderi.gudderibatch.domain.LiveSchedule;
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

    public void setUpArtistLiveData() {
//        List<Artist> artists = artistLiveExtractRepository.getArtistLiveList();
        LiveSchedule liveSchedule = LiveSchedule.builder()
                .liveDate(new Date())
                .livePlace("高円寺")
                .livePrefecture("東京都")
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

    private void commitArtistTransaction(Artist artist) {
        int artistId = artistLiveRepository.insertArtist(artist);
        artist.getLiveList().forEach(live -> {
            int liveId = artistLiveRepository.insertLive(artistId, live);
            live.getLiveScheduleList().forEach(liveSchedule -> {
                artistLiveRepository.insertLiveSchedule(liveId, liveSchedule);
            });
        });
    }
}
