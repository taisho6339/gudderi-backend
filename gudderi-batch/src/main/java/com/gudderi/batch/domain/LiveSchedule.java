package com.gudderi.batch.domain;

import com.gudderi.batch.enums.Prefecture;

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