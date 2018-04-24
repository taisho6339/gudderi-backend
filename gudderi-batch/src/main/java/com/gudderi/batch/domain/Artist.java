package com.gudderi.batch.domain;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Artist {
    private int artistId;
    private String artistName;
    private List<Live> liveList;
}