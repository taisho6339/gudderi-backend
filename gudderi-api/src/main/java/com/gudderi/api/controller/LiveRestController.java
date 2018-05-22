package com.gudderi.api.controller;

import com.gudderi.api.component.exception.AlreadyExistException;
import com.gudderi.api.controller.response.LiveListResponse;
import com.gudderi.api.domain.Live;
import com.gudderi.api.service.LiveService;

import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(value = "/api/live", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Slf4j
public class LiveRestController {

    private final LiveService liveService;

    @GetMapping(path = "/{artistId}")
    @ResponseStatus(HttpStatus.OK)
    public LiveListResponse index(@PathVariable int artistId, @PageableDefault(size = 30) Pageable pageable) {
        long totalCount = liveService.countLiveList(artistId);
        if (totalCount < 0) {
            return new LiveListResponse(0, 0, Collections.emptyList());
        }
        List<Live> liveList = liveService.getLiveList(artistId, pageable);
        return new LiveListResponse(totalCount, pageable.getPageNumber(), liveList);
    }

    @PostMapping(path = "/{liveScheduleId}/participants")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public void registerParticipant(@PathVariable int liveScheduleId) throws AlreadyExistException {
        //TODO: 認証情報からParseする
        final int userId = 1;
        liveService.registerLiveParticipant(userId, liveScheduleId);
    }

    @ExceptionHandler(AlreadyExistException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public void handleAlreadyExistException(AlreadyExistException exception) {
        log.warn(exception.getMessage());
    }
}
