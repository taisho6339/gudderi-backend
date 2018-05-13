package com.gudderi.api.controller;

import com.gudderi.api.controller.response.ArtistSearchResponse;
import com.gudderi.api.domain.Artist;
import com.gudderi.api.service.ArtistSearchService;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ArtistSearchRestController {

    private ArtistSearchService artistSearchService;

    public ArtistSearchRestController(@Autowired ArtistSearchService artistSearchService) {
        this.artistSearchService = artistSearchService;
    }

    @GetMapping("/artists")
    public ArtistSearchResponse keywordSearch(@RequestParam(value = "keyword", defaultValue = "") String keyword) {
        if (StringUtils.isBlank(keyword)) {
            return new ArtistSearchResponse(new ArrayList<>());
        }
        List<Artist> artists = artistSearchService.searchArtists(keyword);
        return new ArtistSearchResponse(artists);
    }
}
