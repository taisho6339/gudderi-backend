package com.gudderi.api.repository;

import com.gudderi.api.domain.Artist;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ArtistSearchRepository {
    List<Artist> selectArtists(@Param("keyword") String keyword);
}
