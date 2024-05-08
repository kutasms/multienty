package com.chia.multienty.core.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import com.chia.multienty.core.service.AppInstanceService;
import com.chia.multienty.core.domain.dto.AppInstanceDTO;
import com.chia.multienty.core.pojo.AppInstance;
import com.chia.multienty.core.parameter.tenant.AppInstanceDetailGetParameter;
import com.chia.multienty.core.parameter.tenant.AppInstancePageGetParameter;
import com.chia.multienty.core.parameter.tenant.AppInstanceDeleteParameter;
import com.chia.multienty.core.parameter.tenant.AppInstanceSaveParameter;
import com.chia.multienty.core.parameter.tenant.AppInstanceUpdateParameter;
import com.chia.multienty.core.parameter.tenant.AppInstanceEnableParameter;
import com.chia.multienty.core.parameter.tenant.AppInstanceDisableParameter;
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
 * 应用实例 服务
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-03-27
 */
@RestController
@Validated
@RequestMapping("/app-instance")
@RequiredArgsConstructor
@Api(tags = "应用实例")
public class AppInstanceController {
    private final AppInstanceService appInstanceService;

    @PostMapping("/detail")
    @ApiOperation("获取应用实例详情")
    public Result<AppInstanceDTO> getDetail(@Validated @RequestBody AppInstanceDetailGetParameter parameter) {
        AppInstanceDTO detail = appInstanceService.getDetail(parameter);
        return new Result<>(detail);
    }

    @PostMapping("/page")
    @ApiOperation("获取应用实例分页列表")
    public Result<IPage<AppInstanceDTO>> getPage(@Validated @RequestBody AppInstancePageGetParameter parameter) {
        IPage<AppInstanceDTO> page = appInstanceService.getPage(parameter);
        return new Result<>(page);
    }

    @PostMapping("/update")
    @ApiOperation("更新应用实例")
    @WebLog
    public Result<Boolean> update(@Validated @RequestBody AppInstanceUpdateParameter parameter) {
        appInstanceService.update(parameter);
        return new Result<>(true);
    }

    @PostMapping("/save")
    @ApiOperation("保存应用实例")
    @WebLog
    public Result<Boolean> save(@Validated @RequestBody AppInstanceSaveParameter parameter) {
        appInstanceService.save(parameter);
        return new Result<>(true);
    }

    @PostMapping("/enable")
    @ApiOperation("启用应用实例")
    @WebLog
    public Result<Boolean> enable(@Validated @RequestBody AppInstanceEnableParameter parameter) {
        appInstanceService.enable(parameter);
        return new Result<>(true);
    }

    @PostMapping("/disable")
    @ApiOperation("禁用应用实例")
    @WebLog
    public Result<Boolean> save(@Validated @RequestBody AppInstanceDisableParameter parameter) {
        appInstanceService.disable(parameter);
        return new Result<>(true);
    }

    @DeleteMapping("/delete")
    @ApiOperation("删除应用实例")
    @WebLog
    public Result<Boolean> delete(@Validated @RequestBody AppInstanceDeleteParameter parameter) {
        appInstanceService.delete(parameter);
        return new Result<>(true);
    }

}
