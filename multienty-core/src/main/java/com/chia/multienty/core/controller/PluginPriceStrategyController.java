package com.chia.multienty.core.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.chia.multienty.core.annotation.WebLog;
import com.chia.multienty.core.domain.basic.Result;
import com.chia.multienty.core.domain.dto.PluginPriceStrategyDTO;
import com.chia.multienty.core.parameter.plugin.*;
import com.chia.multienty.core.service.PluginPriceStrategyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
/**
 * <p>
 * 插件价格策略 服务
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-03-15
 */
@RestController
@Validated
@RequestMapping("/plugin-price-strategy")
@RequiredArgsConstructor
@Api(tags = "插件价格策略")
public class PluginPriceStrategyController {
    private final PluginPriceStrategyService pluginPriceStrategyService;

    @PostMapping("/detail")
    @ApiOperation("获取插件价格策略详情")
    public Result<PluginPriceStrategyDTO> getDetail(@Validated @RequestBody PluginPriceStrategyDetailGetParameter parameter) {
        PluginPriceStrategyDTO detail = pluginPriceStrategyService.getDetail(parameter);
        return new Result<>(detail);
    }

    @PostMapping("/page")
    @ApiOperation("获取插件价格策略分页列表")
    public Result<IPage<PluginPriceStrategyDTO>> getPage(@Validated @RequestBody PluginPriceStrategyPageGetParameter parameter) {
        IPage<PluginPriceStrategyDTO> page = pluginPriceStrategyService.getPage(parameter);
        return new Result<>(page);
    }

    @PostMapping("/update")
    @ApiOperation("更新插件价格策略")
    @WebLog(target = "PluginPriceStrategy")
    public Result<Boolean> update(@Validated @RequestBody PluginPriceStrategyUpdateParameter parameter) {
        pluginPriceStrategyService.update(parameter);
        return new Result<>(true);
    }

    @PostMapping("/save")
    @ApiOperation("保存插件价格策略")
    @WebLog(target = "PluginPriceStrategy")
    public Result<Boolean> save(@Validated @RequestBody PluginPriceStrategySaveParameter parameter) {
        pluginPriceStrategyService.save(parameter);
        return new Result<>(true);
    }

    @PostMapping("/enable")
    @ApiOperation("启用插件价格策略")
    @WebLog(target = "PluginPriceStrategy")
    public Result<Boolean> enable(@Validated @RequestBody PluginPriceStrategyEnableParameter parameter) {
        pluginPriceStrategyService.enable(parameter);
        return new Result<>(true);
    }

    @PostMapping("/disable")
    @ApiOperation("禁用插件价格策略")
    @WebLog(target = "PluginPriceStrategy")
    public Result<Boolean> save(@Validated @RequestBody PluginPriceStrategyDisableParameter parameter) {
        pluginPriceStrategyService.disable(parameter);
        return new Result<>(true);
    }

    @DeleteMapping("/delete")
    @ApiOperation("删除插件价格策略")
    @WebLog(target = "PluginPriceStrategy")
    public Result<Boolean> delete(@Validated @RequestBody PluginPriceStrategyDeleteParameter parameter) {
        pluginPriceStrategyService.delete(parameter);
        return new Result<>(true);
    }

}
