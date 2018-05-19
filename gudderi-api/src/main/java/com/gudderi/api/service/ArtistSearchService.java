package com.gudderi.api.service;

import com.gudderi.api.domain.Artist;
import com.gudderi.api.repository.ArtistSearchRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ArtistSearchService {

    private final ArtistSearchRepository artistSearchRepository;

    public List<Artist> searchArtists(String keyword) {
        /*
         * 最初はミニマムに始めるためDB検索で行うが、
         * いずれはElasticsearchに移行させる
         * ただの前方一致ではなく、表記の揺れなども考慮して検索させたい
         */
        return artistSearchRepository.selectArtists(keyword);
    }
}
