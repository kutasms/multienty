package com.chia.multienty.core.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import com.chia.multienty.core.service.SchedulingJobService;
import com.chia.multienty.core.domain.dto.SchedulingJobDTO;
import com.chia.multienty.core.pojo.SchedulingJob;
import com.chia.multienty.core.parameter.base.SchedulingJobDetailGetParameter;
import com.chia.multienty.core.parameter.base.SchedulingJobPageGetParameter;
import com.chia.multienty.core.parameter.base.SchedulingJobDeleteParameter;
import com.chia.multienty.core.parameter.base.SchedulingJobSaveParameter;
import com.chia.multienty.core.parameter.base.SchedulingJobUpdateParameter;
import com.chia.multienty.core.parameter.base.SchedulingJobEnableParameter;
import com.chia.multienty.core.parameter.base.SchedulingJobDisableParameter;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.chia.multienty.core.annotation.WebLog;
import com.chia.multienty.core.domain.basic.Result;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
/**
 * <p>
 * 调度任务 服务
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-04-10
 */
@RestController
@Validated
@RequestMapping("/scheduling-job")
@RequiredArgsConstructor
@Api(tags = "调度任务")
public class SchedulingJobController {

    private final SchedulingJobService schedulingJobService;

    @PostMapping("/detail")
    @ApiOperation("获取调度任务详情")
    public Result<SchedulingJobDTO> getDetail(@Validated @RequestBody SchedulingJobDetailGetParameter parameter) {
        SchedulingJobDTO detail = schedulingJobService.getDetail(parameter);
        return new Result<>(detail);
    }

    @PostMapping("/page")
    @ApiOperation("获取调度任务分页列表")
    public Result<IPage<SchedulingJobDTO>> getPage(@Validated @RequestBody SchedulingJobPageGetParameter parameter) {
        IPage<SchedulingJobDTO> page = schedulingJobService.getPage(parameter);
        return new Result<>(page);
    }

    @PostMapping("/update")
    @ApiOperation("更新调度任务")
    @WebLog(target = "SchedulingJob")
    public Result<Boolean> update(@Validated @RequestBody SchedulingJobUpdateParameter parameter) {
        schedulingJobService.update(parameter);
        return new Result<>(true);
    }

    @PostMapping("/save")
    @ApiOperation("保存调度任务")
    @WebLog(target = "SchedulingJob")
    public Result<Boolean> save(@Validated @RequestBody SchedulingJobSaveParameter parameter) {
        schedulingJobService.save(parameter);
        return new Result<>(true);
    }

    @PostMapping("/enable")
    @ApiOperation("启用调度任务")
    @WebLog(target = "SchedulingJob")
    public Result<Boolean> enable(@Validated @RequestBody SchedulingJobEnableParameter parameter) {
        schedulingJobService.enable(parameter);
        return new Result<>(true);
    }

    @PostMapping("/disable")
    @ApiOperation("禁用调度任务")
    @WebLog(target = "SchedulingJob")
    public Result<Boolean> disable(@Validated @RequestBody SchedulingJobDisableParameter parameter) {
        schedulingJobService.disable(parameter);
        return new Result<>(true);
    }

    @DeleteMapping("/delete")
    @ApiOperation("删除调度任务")
    @WebLog(target = "SchedulingJob")
    public Result<Boolean> delete(@Validated @RequestBody SchedulingJobDeleteParameter parameter) {
        schedulingJobService.delete(parameter);
        return new Result<>(true);
    }

}
