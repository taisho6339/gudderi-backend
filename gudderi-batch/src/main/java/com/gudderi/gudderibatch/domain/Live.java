
package com.gudderi.gudderibatch.domain;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Live {
    private String liveName;
    private List<LiveSchedule> liveScheduleList;
}