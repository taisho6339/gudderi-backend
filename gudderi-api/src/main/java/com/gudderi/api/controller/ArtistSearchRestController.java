package com.gudderi.api.controller;

import com.gudderi.api.controller.request.ArtistSearchRequest;
import com.gudderi.api.controller.response.ArtistSearchResponse;
import com.gudderi.api.domain.Artist;
import com.gudderi.api.service.ArtistSearchService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ArtistSearchRestController {

    private ArtistSearchService artistSearchService;

    public ArtistSearchRestController(@Autowired ArtistSearchService artistSearchService) {
        this.artistSearchService = artistSearchService;
    }

    @GetMapping("/artists")
    public ArtistSearchResponse keywordSearch(@RequestBody @Validated ArtistSearchRequest artistSearchRequest) {
        List<Artist> artists = artistSearchService.searchArtists(artistSearchRequest.getKeyword());
        return new ArtistSearchResponse(artists);
    }
}
