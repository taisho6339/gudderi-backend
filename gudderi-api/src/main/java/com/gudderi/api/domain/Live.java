package com.gudderi.api.domain;

import com.gudderi.core.enums.Prefecture;

import java.util.Date;

import lombok.Data;

@Data
public class Live {
    private int artistId;
    private int liveId;
    private String liveName;
    private String livePlace;
    private Prefecture livePrefecture;
    private Date liveDate;
}
