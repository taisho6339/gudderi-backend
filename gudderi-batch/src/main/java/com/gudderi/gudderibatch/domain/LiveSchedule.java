package com.gudderi.gudderibatch.domain;

import java.util.Date;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LiveSchedule {
    private String livePrefecture;
    private String livePlace;
    private Date liveDate;
}