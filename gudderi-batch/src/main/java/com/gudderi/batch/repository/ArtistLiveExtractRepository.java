package com.gudderi.batch.repository;

import com.gudderi.batch.domain.Artist;

import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * グループとそれに紐づくライブ情報を取得するRepository
 */
@Repository
public interface ArtistLiveExtractRepository {
    public List<Artist> getArtistLiveList();
}
