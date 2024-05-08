package com.chia.multienty.core.scheduling;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SchedulingJobRunningState {
    STOPPED((byte)1, "已停止"),
    RUNNING((byte)2, "运行中");
    private Byte value;
    private String describe;
}
