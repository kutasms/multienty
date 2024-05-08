package com.chia.multienty.core.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.chia.multienty.core.annotation.WebLog;
import com.chia.multienty.core.domain.basic.Result;
import com.chia.multienty.core.domain.dto.PluginDTO;
import com.chia.multienty.core.parameter.plugin.*;
import com.chia.multienty.core.service.PluginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
/**
 * <p>
 * 插件 服务
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-03-15
 */
@RestController
@Validated
@RequestMapping("/plugin")
@RequiredArgsConstructor
@Api(tags = "插件")
public class PluginController {
    private final PluginService pluginService;

    @PostMapping("/detail")
    @ApiOperation("获取插件详情")
    public Result<PluginDTO> getDetail(@Validated @RequestBody PluginDetailGetParameter parameter) {
        PluginDTO detail = pluginService.getDetail(parameter);
        return new Result<>(detail);
    }

    @PostMapping("/page")
    @ApiOperation("获取插件分页列表")
    public Result<IPage<PluginDTO>> getPage(@Validated @RequestBody PluginPageGetParameter parameter) {
        IPage<PluginDTO> page = pluginService.getPage(parameter);
        return new Result<>(page);
    }

    @PostMapping("/update")
    @ApiOperation("更新插件")
    @WebLog(target = "Plugin")
    public Result<Boolean> update(@Validated @RequestBody PluginUpdateParameter parameter) {
        pluginService.update(parameter);
        return new Result<>(true);
    }

    @PostMapping("/save")
    @ApiOperation("保存插件")
    @WebLog(target = "Plugin")
    public Result<Boolean> save(@Validated @RequestBody PluginSaveParameter parameter) {
        pluginService.save(parameter);
        return new Result<>(true);
    }

    @PostMapping("/enable")
    @ApiOperation("启用插件")
    @WebLog(target = "Plugin")
    public Result<Boolean> enable(@Validated @RequestBody PluginEnableParameter parameter) {
        pluginService.enable(parameter);
        return new Result<>(true);
    }

    @PostMapping("/disable")
    @ApiOperation("禁用插件")
    @WebLog(target = "Plugin")
    public Result<Boolean> save(@Validated @RequestBody PluginDisableParameter parameter) {
        pluginService.disable(parameter);
        return new Result<>(true);
    }

    @DeleteMapping("/delete")
    @ApiOperation("删除插件")
    @WebLog(target = "Plugin")
    public Result<Boolean> delete(@Validated @RequestBody PluginDeleteParameter parameter) {
        pluginService.delete(parameter);
        return new Result<>(true);
    }

}
