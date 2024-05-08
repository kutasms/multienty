package com.chia.multienty.core.scheduling;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.chia.multienty.core.domain.dto.SchedulingJobDTO;
import com.chia.multienty.core.domain.enums.StatusEnum;
import com.chia.multienty.core.parameter.base.SchedulingJobPageGetParameter;
import com.chia.multienty.core.service.SchedulingJobService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@ConditionalOnProperty(prefix = "spring.multienty.scheduling", name = "enabled", havingValue = "true")
public class SysJobRunner implements CommandLineRunner {

    @Autowired
    private SchedulingJobService schedulingJobService;
    @Autowired
    private TaskRegistrar taskRegistrar;
    @Override
    public void run(String... args) throws Exception {
        SchedulingJobPageGetParameter parameter = new SchedulingJobPageGetParameter();
        parameter.setPageSize(100);
        parameter.setCurrentPage(1);
        parameter.setStatus(StatusEnum.NORMAL.getCode());
        parameter.setRunningState(SchedulingJobRunningState.RUNNING.getValue());
        IPage<SchedulingJobDTO> page = schedulingJobService.getPage(parameter);
        if(page.getRecords().size() > 0) {
            for (SchedulingJobDTO job : page.getRecords()) {
                SchedulingRunnable task = new SchedulingRunnable(job);
                taskRegistrar.addCronTask(task, job.getCronExp());
            }
            log.info("定时任务已加载完毕...");
        }
    }
}
