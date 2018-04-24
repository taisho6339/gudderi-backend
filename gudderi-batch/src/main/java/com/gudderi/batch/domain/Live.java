
package com.gudderi.batch.domain;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Live {
    private int liveId;
    private String liveName;
    private List<LiveSchedule> liveScheduleList;
}