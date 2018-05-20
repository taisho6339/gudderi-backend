package com.gudderi.api.controller.response;

import com.gudderi.api.domain.Live;

import java.util.List;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class LiveListResponse {
    public long totalCount;
    public long currentPage = 0;
    public List<Live> liveList;
}
