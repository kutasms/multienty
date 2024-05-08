package com.chia.multienty.core.scheduling;

import lombok.Getter;
import org.springframework.scheduling.config.Task;

import java.time.Instant;
@Getter
public class OnceTask extends Task {
    private Instant instant;
    /**
     * Create a new {@code Task}.
     *
     * @param runnable the underlying task to execute
     */
    public OnceTask(Runnable runnable) {
        super(runnable);
    }
    public OnceTask(Runnable runnable, Instant instant) {
        this(runnable);
        this.instant = instant;
    }
}
