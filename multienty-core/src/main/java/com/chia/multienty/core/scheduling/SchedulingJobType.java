package com.chia.multienty.core.scheduling;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SchedulingJobType {
    CRON((byte)1, "定时任务"),
    ONCE((byte)2, "一次性任务");
    private Byte value;
    private String describe;
}
