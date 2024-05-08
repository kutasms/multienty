package com.chia.multienty.core.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import com.chia.multienty.core.service.AppService;
import com.chia.multienty.core.domain.dto.AppDTO;
import com.chia.multienty.core.pojo.App;
import com.chia.multienty.core.parameter.base.AppDetailGetParameter;
import com.chia.multienty.core.parameter.base.AppPageGetParameter;
import com.chia.multienty.core.parameter.base.AppDeleteParameter;
import com.chia.multienty.core.parameter.base.AppSaveParameter;
import com.chia.multienty.core.parameter.base.AppUpdateParameter;
import com.chia.multienty.core.parameter.base.AppEnableParameter;
import com.chia.multienty.core.parameter.base.AppDisableParameter;
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
 * 应用 服务
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-03-27
 */
@RestController
@Validated
@RequestMapping("/app")
@RequiredArgsConstructor
@Api(tags = "应用")
public class AppController {
    private final AppService appService;

    @PostMapping("/detail")
    @ApiOperation("获取应用详情")
    public Result<AppDTO> getDetail(@Validated @RequestBody AppDetailGetParameter parameter) {
        AppDTO detail = appService.getDetail(parameter);
        return new Result<>(detail);
    }

    @PostMapping("/page")
    @ApiOperation("获取应用分页列表")
    public Result<IPage<AppDTO>> getPage(@Validated @RequestBody AppPageGetParameter parameter) {
        IPage<AppDTO> page = appService.getPage(parameter);
        return new Result<>(page);
    }

    @PostMapping("/update")
    @ApiOperation("更新应用")
    @WebLog
    public Result<Boolean> update(@Validated @RequestBody AppUpdateParameter parameter) {
        appService.update(parameter);
        return new Result<>(true);
    }

    @PostMapping("/save")
    @ApiOperation("保存应用")
    @WebLog
    public Result<Boolean> save(@Validated @RequestBody AppSaveParameter parameter) {
        appService.save(parameter);
        return new Result<>(true);
    }

    @PostMapping("/enable")
    @ApiOperation("启用应用")
    @WebLog
    public Result<Boolean> enable(@Validated @RequestBody AppEnableParameter parameter) {
        appService.enable(parameter);
        return new Result<>(true);
    }

    @PostMapping("/disable")
    @ApiOperation("禁用应用")
    @WebLog
    public Result<Boolean> save(@Validated @RequestBody AppDisableParameter parameter) {
        appService.disable(parameter);
        return new Result<>(true);
    }

    @DeleteMapping("/delete")
    @ApiOperation("删除应用")
    @WebLog
    public Result<Boolean> delete(@Validated @RequestBody AppDeleteParameter parameter) {
        appService.delete(parameter);
        return new Result<>(true);
    }

}
