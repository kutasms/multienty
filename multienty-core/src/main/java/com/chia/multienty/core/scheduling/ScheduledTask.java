package com.chia.multienty.core.scheduling;

import java.util.concurrent.ScheduledFuture;

public class ScheduledTask {
    volatile ScheduledFuture<?> future;

    public void cancel() {
        ScheduledFuture<?> future = this.future;
        if(future != null) {
            future.cancel(true);
        }
    }
}
