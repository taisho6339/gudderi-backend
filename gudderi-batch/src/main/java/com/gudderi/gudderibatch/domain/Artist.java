package com.gudderi.gudderibatch.domain;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Artist {
    private String artistName;
    private List<Live> liveList;
}