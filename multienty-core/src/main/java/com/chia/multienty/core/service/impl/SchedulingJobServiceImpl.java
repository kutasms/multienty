package com.chia.multienty.core.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.chia.multienty.core.domain.dto.SchedulingJobDTO;
import com.chia.multienty.core.domain.enums.StatusEnum;
import com.chia.multienty.core.mapper.SchedulingJobMapper;
import com.chia.multienty.core.mybatis.MTLambdaWrapper;
import com.chia.multienty.core.mybatis.service.impl.KutaBaseServiceImpl;
import com.chia.multienty.core.parameter.base.*;
import com.chia.multienty.core.pojo.SchedulingJob;
import com.chia.multienty.core.scheduling.SchedulingJobRunningState;
import com.chia.multienty.core.scheduling.SchedulingJobType;
import com.chia.multienty.core.scheduling.SchedulingRunnable;
import com.chia.multienty.core.scheduling.TaskRegistrar;
import com.chia.multienty.core.service.SchedulingJobService;
import com.chia.multienty.core.tools.IdWorkerProvider;
import com.chia.multienty.core.util.ListUtil;
import com.github.yulichang.toolkit.MPJWrappers;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 调度任务 服务实现类
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-04-10
 */
@Service
@RequiredArgsConstructor
public class SchedulingJobServiceImpl extends KutaBaseServiceImpl<SchedulingJobMapper, SchedulingJob> implements SchedulingJobService {

    private final TaskRegistrar taskRegistrar;

    @Override
    public SchedulingJobDTO getDetail(SchedulingJobDetailGetParameter parameter) {
        return selectJoinOne(SchedulingJobDTO.class,
                        MPJWrappers.<SchedulingJob>lambdaJoin().eq(SchedulingJob::getJobId, parameter.getJobId()));
    }

    @Override
    public void update(SchedulingJobUpdateParameter parameter) {
        SchedulingJob schedulingJob = new SchedulingJob();
        BeanUtils.copyProperties(parameter, schedulingJob);
        updateByIdTE(schedulingJob);
        schedulingJob = getById(parameter.getJobId());
        if(
                schedulingJob.getStatus().equals(StatusEnum.NORMAL.getCode()) &&
                        schedulingJob.getRunningState().equals(SchedulingJobRunningState.RUNNING.getValue())) {
            taskRegistrar.removeTask(
                    new SchedulingRunnable(
                            schedulingJob
                    ));
        }
        if(schedulingJob.getType().equals(SchedulingJobType.CRON.getValue())) {
            taskRegistrar.addCronTask(
                    new SchedulingRunnable(schedulingJob), schedulingJob.getCronExp());
        } else if (schedulingJob.getType().equals(SchedulingJobType.ONCE.getValue())) {
            taskRegistrar.addOnceTask(
                    new SchedulingRunnable(schedulingJob), schedulingJob.getStartTime());
        }
    }

    @Override
    @Transactional(isolation= Isolation.DEFAULT,propagation= Propagation.REQUIRED,rollbackFor = {
            Exception.class})
    public void delete(SchedulingJobDeleteParameter parameter) {
        SchedulingJob job = getById(parameter.getJobId());
        removeByIdTE(parameter.getJobId());
        if(
                job.getStatus().equals(StatusEnum.NORMAL.getCode()) &&
                job.getRunningState().equals(SchedulingJobRunningState.RUNNING.getValue())) {
            taskRegistrar.removeTask(
                    new SchedulingRunnable(job));
        }
    }

    @Override
    public IPage<SchedulingJobDTO> getPage(SchedulingJobPageGetParameter parameter) {
        return selectJoinListPage(parameter.getPageObj(), SchedulingJobDTO.class,
                new MTLambdaWrapper<SchedulingJob>()
                        .solveGenericParameters(parameter)
                        .in(!ListUtil.isEmpty(parameter.getJobIds()),
                                SchedulingJob::getJobId,
                                parameter.getJobIds())
        );
    }

    @Override
    @Transactional(isolation= Isolation.DEFAULT,propagation= Propagation.REQUIRED,rollbackFor = {
            Exception.class})
    public void save(SchedulingJobSaveParameter parameter) {
        SchedulingJob schedulingJob = new SchedulingJob();
        BeanUtils.copyProperties(parameter, schedulingJob);
        schedulingJob.setJobId(IdWorkerProvider.next());
        saveTE(schedulingJob);
        if(

                schedulingJob.getStatus().equals(StatusEnum.NORMAL.getCode()) &&
                        schedulingJob.getRunningState().equals(SchedulingJobRunningState.RUNNING.getValue())) {
            if(schedulingJob.getType().equals(SchedulingJobType.CRON.getValue())) {
                taskRegistrar.addCronTask(new SchedulingRunnable(
                        schedulingJob
                ), schedulingJob.getCronExp());
            }
            else if(schedulingJob.getType().equals(SchedulingJobType.ONCE.getValue())) {
                taskRegistrar.addOnceTask(new SchedulingRunnable(
                        schedulingJob
                ), schedulingJob.getStartTime());
            }
        }

        parameter.setJobId(schedulingJob.getJobId());
    }


    @Override
    @Transactional(isolation= Isolation.DEFAULT,propagation= Propagation.REQUIRED,rollbackFor = {
            Exception.class})
    public void enable(SchedulingJobEnableParameter parameter) {
        SchedulingJob schedulingJob = new SchedulingJob();
        BeanUtils.copyProperties(parameter, schedulingJob);
        schedulingJob.setStatus(StatusEnum.NORMAL.getCode());
        schedulingJob.setRunningState(SchedulingJobRunningState.RUNNING.getValue());
        updateByIdTE(schedulingJob);
        schedulingJob = getById(parameter.getJobId());
        if(schedulingJob.getType().equals(SchedulingJobType.CRON.getValue())) {
            taskRegistrar.addCronTask(new SchedulingRunnable(
                    schedulingJob
            ), schedulingJob.getCronExp());
        }
        else if(schedulingJob.getType().equals(SchedulingJobType.ONCE.getValue())) {
            taskRegistrar.addOnceTask(new SchedulingRunnable(
                    schedulingJob
            ), schedulingJob.getStartTime());
        }
    }

    @Override
    @Transactional(isolation= Isolation.DEFAULT,propagation= Propagation.REQUIRED,rollbackFor = {
            Exception.class})
    public void disable(SchedulingJobDisableParameter parameter) {
        SchedulingJob schedulingJob = new SchedulingJob();
        BeanUtils.copyProperties(parameter, schedulingJob);
        schedulingJob.setStatus(StatusEnum.DISABLED.getCode());
        schedulingJob.setRunningState(SchedulingJobRunningState.STOPPED.getValue());
        updateByIdTE(schedulingJob);
        schedulingJob = getById(parameter.getJobId());
        if(
                schedulingJob.getStatus().equals(StatusEnum.NORMAL.getCode()) &&
                        schedulingJob.getRunningState().equals(SchedulingJobRunningState.RUNNING.getValue())) {
            taskRegistrar.removeTask(
                    new SchedulingRunnable(
                            schedulingJob));
        }
    }
}
