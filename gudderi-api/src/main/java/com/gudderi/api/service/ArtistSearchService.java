package com.gudderi.api.service;

import com.gudderi.api.domain.Artist;
import com.gudderi.api.repository.ArtistSearchRepository;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class ArtistSearchService {

    private ArtistSearchRepository artistSearchRepository;

    public ArtistSearchService(@Autowired ArtistSearchRepository artistSearchRepository) {
        this.artistSearchRepository = artistSearchRepository;
    }

    public List<Artist> searchArtists(String keyword) {
        if (StringUtils.isBlank(keyword)) {
            return Collections.emptyList();
        }
        /*
         * 最初はミニマムに始めるためDB検索で行うが、
         * いずれはElasticsearchに移行させる
         * ただの前方一致ではなく、表記の揺れなども考慮して検索させたい
         */
        return artistSearchRepository.selectArtists(keyword);
    }
}
