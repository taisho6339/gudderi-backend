package com.gudderi.api.service;

import com.gudderi.api.component.exception.AlreadyExistException;
import com.gudderi.api.domain.Live;
import com.gudderi.api.repository.LiveRepository;
import com.gudderi.core.annotation.GudderiTransactional;

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

    @GudderiTransactional
    public void registerLiveParticipant(int liveScheduleId, int userId) throws AlreadyExistException {
        if (liveRepository.existLiveParticipant(userId, liveScheduleId)) {
            throw new AlreadyExistException(
                    "ライブ参加者情報はすでに登録済みです。 userId = " + userId + ",liveScheduleId = " + liveScheduleId
            );
        }
        liveRepository.insertParticipantUser(userId, liveScheduleId);
    }
}
