package com.chia.multienty.core.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.chia.multienty.core.annotation.WebLog;
import com.chia.multienty.core.domain.basic.Result;
import com.chia.multienty.core.domain.dto.WechatMppAuthTaskDTO;
import com.chia.multienty.core.parameter.wechat.*;
import com.chia.multienty.core.service.WechatMppAuthTaskService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
/**
 * <p>
 * 微信小程序认证任务 服务
 * </p>
 *
 * @author Multienty Auto Generator
 * @since 2024-04-16
 */
@RestController
@Validated
@RequestMapping("/wechat-mpp-auth-task")
@RequiredArgsConstructor
@Api(tags = "微信小程序认证任务")
@ConditionalOnProperty(prefix = "spring.multienty", name = "wechat-module-enabled", havingValue = "true")
public class WechatMppAuthTaskController {

    private final WechatMppAuthTaskService wechatMppAuthTaskService;

    @PostMapping("/detail")
    @ApiOperation("获取微信小程序认证任务详情")
    public Result<WechatMppAuthTaskDTO> getDetail(@Validated @RequestBody WechatMppAuthTaskDetailGetParameter parameter) {
        WechatMppAuthTaskDTO detail = wechatMppAuthTaskService.getDetail(parameter);
        return new Result<>(detail);
    }

    @PostMapping("/page")
    @ApiOperation("获取微信小程序认证任务分页列表")
    public Result<IPage<WechatMppAuthTaskDTO>> getPage(@Validated @RequestBody WechatMppAuthTaskPageGetParameter parameter) {
        IPage<WechatMppAuthTaskDTO> page = wechatMppAuthTaskService.getPage(parameter);
        return new Result<>(page);
    }

    @PostMapping("/update")
    @ApiOperation("更新微信小程序认证任务")
    @WebLog(target = "WechatMppAuthTask")
    public Result<Boolean> update(@Validated @RequestBody WechatMppAuthTaskUpdateParameter parameter) {
        wechatMppAuthTaskService.update(parameter);
        return new Result<>(true);
    }

    @PostMapping("/save")
    @ApiOperation("保存微信小程序认证任务")
    @WebLog(target = "WechatMppAuthTask")
    public Result<Boolean> save(@Validated @RequestBody WechatMppAuthTaskSaveParameter parameter) {
        wechatMppAuthTaskService.save(parameter);
        return new Result<>(true);
    }

    @PostMapping("/enable")
    @ApiOperation("启用微信小程序认证任务")
    @WebLog(target = "WechatMppAuthTask")
    public Result<Boolean> enable(@Validated @RequestBody WechatMppAuthTaskEnableParameter parameter) {
        wechatMppAuthTaskService.enable(parameter);
        return new Result<>(true);
    }

    @PostMapping("/disable")
    @ApiOperation("禁用微信小程序认证任务")
    @WebLog(target = "WechatMppAuthTask")
    public Result<Boolean> disable(@Validated @RequestBody WechatMppAuthTaskDisableParameter parameter) {
        wechatMppAuthTaskService.disable(parameter);
        return new Result<>(true);
    }

    @DeleteMapping("/delete")
    @ApiOperation("删除微信小程序认证任务")
    @WebLog(target = "WechatMppAuthTask")
    public Result<Boolean> delete(@Validated @RequestBody WechatMppAuthTaskDeleteParameter parameter) {
        wechatMppAuthTaskService.delete(parameter);
        return new Result<>(true);
    }

}
