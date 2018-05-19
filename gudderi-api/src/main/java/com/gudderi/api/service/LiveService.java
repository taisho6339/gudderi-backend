package com.gudderi.api.service;

import com.gudderi.api.domain.Live;
import com.gudderi.api.repository.LiveRepository;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LiveService {

    private final LiveRepository liveRepository;

    public List<Live> getLiveList(int artistId, Pageable pageable) {
        return liveRepository.selectLiveList(artistId, pageable);
    }

    public long countLiveList(int artistId) {
        return liveRepository.countLiveList(artistId);
    }
}
