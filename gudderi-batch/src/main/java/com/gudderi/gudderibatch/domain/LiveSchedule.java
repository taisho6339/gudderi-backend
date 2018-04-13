package com.gudderi.gudderibatch.domain;

import com.gudderi.gudderibatch.enums.Prefecture;

import java.util.Date;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LiveSchedule {
    private Prefecture livePrefecture;
    private String livePlace;
    private Date liveDate;
}