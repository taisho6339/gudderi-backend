package com.gudderi.gudderibatch.repository;

import com.gudderi.gudderibatch.domain.Artist;

import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * グループとそれに紐づくライブ情報を取得するRepository
 */
@Repository
public interface ArtistLiveExtractRepository {
    public List<Artist> getArtistLiveList();
}
