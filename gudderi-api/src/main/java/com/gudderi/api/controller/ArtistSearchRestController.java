package com.gudderi.api.controller;

import com.gudderi.api.controller.response.ArtistSearchResponse;
import com.gudderi.api.domain.Artist;
import com.gudderi.api.service.ArtistSearchService;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class ArtistSearchRestController {

    private final ArtistSearchService artistSearchService;

    @GetMapping("/artists")
    public ArtistSearchResponse keywordSearch(@RequestParam(value = "keyword", defaultValue = "") String keyword) {
        // 大した件数にならないので全件返す
        if (StringUtils.isBlank(keyword)) {
            return new ArtistSearchResponse(0, Collections.emptyList());
        }
        List<Artist> artists = artistSearchService.searchArtists(keyword);
        return new ArtistSearchResponse(artists.size(), artists);
    }
}
