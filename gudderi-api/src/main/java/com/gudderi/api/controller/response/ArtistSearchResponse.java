package com.gudderi.api.controller.response;

import com.gudderi.api.domain.Artist;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
public class ArtistSearchResponse {
    public long totalCount;
    public List<Artist> artistList;
}
