package com.chia.multienty.core.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.chia.multienty.core.annotation.WebLog;
import com.chia.multienty.core.domain.basic.Result;
import com.chia.multienty.core.domain.dto.PluginContractDTO;
import com.chia.multienty.core.parameter.plugin.*;
import com.chia.multienty.core.service.PluginContractService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
/**
 * <p>
 * 插件合约 服务
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-03-15
 */
@RestController
@Validated
@RequestMapping("/plugin-contract")
@RequiredArgsConstructor
@Api(tags = "插件合约")
public class PluginContractController {
    private final PluginContractService pluginContractService;

    @PostMapping("/detail")
    @ApiOperation("获取插件合约详情")
    public Result<PluginContractDTO> getDetail(@Validated @RequestBody PluginContractDetailGetParameter parameter) {
        PluginContractDTO detail = pluginContractService.getDetail(parameter);
        return new Result<>(detail);
    }

    @PostMapping("/page")
    @ApiOperation("获取插件合约分页列表")
    public Result<IPage<PluginContractDTO>> getPage(@Validated @RequestBody PluginContractPageGetParameter parameter) {
        IPage<PluginContractDTO> page = pluginContractService.getPage(parameter);
        return new Result<>(page);
    }

    @PostMapping("/update")
    @ApiOperation("更新插件合约")
    @WebLog(target = "PluginContract")
    public Result<Boolean> update(@Validated @RequestBody PluginContractUpdateParameter parameter) {
        pluginContractService.update(parameter);
        return new Result<>(true);
    }

    @PostMapping("/save")
    @ApiOperation("保存插件合约")
    @WebLog(target = "PluginContract")
    public Result<Boolean> save(@Validated @RequestBody PluginContractSaveParameter parameter) {
        pluginContractService.save(parameter);
        return new Result<>(true);
    }

    @PostMapping("/enable")
    @ApiOperation("启用插件合约")
    @WebLog(target = "PluginContract")
    public Result<Boolean> enable(@Validated @RequestBody PluginContractEnableParameter parameter) {
        pluginContractService.enable(parameter);
        return new Result<>(true);
    }

    @PostMapping("/disable")
    @ApiOperation("禁用插件合约")
    @WebLog(target = "PluginContract")
    public Result<Boolean> save(@Validated @RequestBody PluginContractDisableParameter parameter) {
        pluginContractService.disable(parameter);
        return new Result<>(true);
    }

    @DeleteMapping("/delete")
    @ApiOperation("删除插件合约")
    @WebLog(target = "PluginContract")
    public Result<Boolean> delete(@Validated @RequestBody PluginContractDeleteParameter parameter) {
        pluginContractService.delete(parameter);
        return new Result<>(true);
    }

}
