package com.chia.multienty.core.scheduling;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.config.CronTask;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class TaskRegistrar implements DisposableBean {

    private final Map<Runnable, ScheduledTask> scheduledTasks = new ConcurrentHashMap<>(16);

    @Autowired
    private TaskScheduler taskScheduler;

    public TaskScheduler getScheduler() {
        return this.taskScheduler;
    }

    public void addOnceTask(Runnable task, LocalDateTime startTime) {
        addOnceTask(new OnceTask(task, startTime.toInstant(ZoneOffset.of("+8"))));
    }

    public void addCronTask(Runnable task, String cronExpression) {
        addCronTask(new CronTask(task, cronExpression));
    }

    public void addCronTask(CronTask cronTask) {
        if (cronTask != null) {
            Runnable task = cronTask.getRunnable();
            if (this.scheduledTasks.containsKey(task)) {
                removeTask(task);
            }

            this.scheduledTasks.put(task, scheduleCronTask(cronTask));
        }
    }

    public void addOnceTask(OnceTask onceTask) {
        if(onceTask != null) {
            Runnable task = onceTask.getRunnable();
            if(this.scheduledTasks.containsKey(task)) {
                removeTask(task);
            }
            this.scheduledTasks.put(task, scheduleOnceTask(onceTask));
        }
    }

    public void removeTask(Runnable task) {
        ScheduledTask scheduledTask = this.scheduledTasks.remove(task);
        if (scheduledTask != null)
            scheduledTask.cancel();
    }


    public ScheduledTask scheduleCronTask(CronTask cronTask) {
        ScheduledTask scheduledTask = new ScheduledTask();
        scheduledTask.future = this.taskScheduler.schedule(cronTask.getRunnable(), cronTask.getTrigger());
        return scheduledTask;
    }

    public ScheduledTask scheduleOnceTask(OnceTask onceTask) {
        ScheduledTask scheduledTask = new ScheduledTask();
        scheduledTask.future =  this.taskScheduler.schedule(onceTask.getRunnable(), onceTask.getInstant());
        return scheduledTask;
    }

    @Override
    public void destroy() {
        for (ScheduledTask task : this.scheduledTasks.values()) {
            task.cancel();
        }

        this.scheduledTasks.clear();
    }
}
