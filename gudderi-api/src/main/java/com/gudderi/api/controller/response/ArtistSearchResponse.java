package com.gudderi.api.controller.response;

import com.gudderi.api.domain.Artist;

import java.util.List;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ArtistSearchResponse {
    private List<Artist> artistList;
}
