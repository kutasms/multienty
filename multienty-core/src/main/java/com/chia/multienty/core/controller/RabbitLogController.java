package com.chia.multienty.core.controller;


import com.chia.multienty.core.annotation.WebLog;
import com.chia.multienty.core.domain.basic.Result;
import com.chia.multienty.core.domain.dto.RabbitLogDTO;
import com.chia.multienty.core.service.RabbitLogService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.web.bind.annotation.RequestMapping;
import com.chia.multienty.core.parameter.log.RabbitLogDetailGetParameter;
import com.chia.multienty.core.parameter.log.RabbitLogPageGetParameter;
import com.chia.multienty.core.parameter.log.RabbitLogDeleteParameter;
import com.chia.multienty.core.parameter.log.RabbitLogSaveParameter;
import com.chia.multienty.core.parameter.log.RabbitLogUpdateParameter;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
/**
 * <p>
 * RabbitMQ日志前端控制器
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-01-10
 */
@RestController
@RequestMapping("/rabbit-log")
@RequiredArgsConstructor
@Api(tags = "RabbitMQ日志前端控制器")
@ConditionalOnProperty(prefix = "spring.multienty", name = "base-module-enabled", havingValue = "true")
public class RabbitLogController {
    private final RabbitLogService rabbitLogService;

    @PostMapping("/detail")
    @ApiOperation("获取Rabbit MQ日志信息详情")
    public Result<RabbitLogDTO> getDetail(@RequestBody RabbitLogDetailGetParameter parameter) {
        RabbitLogDTO detail = rabbitLogService.getDetail(parameter);
        return new Result<>(detail);
    }

    @PostMapping("/page")
    @ApiOperation("获取Rabbit MQ日志信息分页列表")
    public Result<IPage<RabbitLogDTO>> getPage(@RequestBody RabbitLogPageGetParameter parameter) {
        IPage<RabbitLogDTO> page = rabbitLogService.getPage(parameter);
        return new Result<>(page);
    }

    @PostMapping("/update")
    @ApiOperation("更新Rabbit MQ日志信息")
    @WebLog(target = "RabbitLog")
    public Result<Boolean> update(@RequestBody RabbitLogUpdateParameter parameter) {
        rabbitLogService.update(parameter);
        return new Result<>(true);
    }

    @PostMapping("/save")
    @ApiOperation("保存Rabbit MQ日志信息")
    @WebLog(target = "RabbitLog")
    public Result<Boolean> save(@RequestBody RabbitLogSaveParameter parameter) {
        rabbitLogService.save(parameter);
        return new Result<>(true);
    }

    @DeleteMapping("/delete")
    @ApiOperation("删除Rabbit MQ日志信息")
    @WebLog(target = "RabbitLog")
    public Result<Boolean> delete(@RequestBody RabbitLogDeleteParameter parameter) {
        rabbitLogService.delete(parameter);
        return new Result<>(true);
    }
}
