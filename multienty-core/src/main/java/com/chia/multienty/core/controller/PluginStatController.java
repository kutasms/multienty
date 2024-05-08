package com.chia.multienty.core.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.chia.multienty.core.annotation.WebLog;
import com.chia.multienty.core.domain.basic.Result;
import com.chia.multienty.core.domain.dto.PluginStatDTO;
import com.chia.multienty.core.parameter.plugin.*;
import com.chia.multienty.core.service.PluginStatService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
/**
 * <p>
 * 插件统计 服务
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-03-15
 */
@RestController
@Validated
@RequestMapping("/plugin-stat")
@RequiredArgsConstructor
@Api(tags = "插件统计")
public class PluginStatController {
    private final PluginStatService pluginStatService;

    @PostMapping("/detail")
    @ApiOperation("获取插件统计详情")
    public Result<PluginStatDTO> getDetail(@Validated @RequestBody PluginStatDetailGetParameter parameter) {
        PluginStatDTO detail = pluginStatService.getDetail(parameter);
        return new Result<>(detail);
    }

    @PostMapping("/page")
    @ApiOperation("获取插件统计分页列表")
    public Result<IPage<PluginStatDTO>> getPage(@Validated @RequestBody PluginStatPageGetParameter parameter) {
        IPage<PluginStatDTO> page = pluginStatService.getPage(parameter);
        return new Result<>(page);
    }

    @PostMapping("/update")
    @ApiOperation("更新插件统计")
    @WebLog(target = "PluginStat")
    public Result<Boolean> update(@Validated @RequestBody PluginStatUpdateParameter parameter) {
        pluginStatService.update(parameter);
        return new Result<>(true);
    }

    @PostMapping("/save")
    @ApiOperation("保存插件统计")
    @WebLog(target = "PluginStat")
    public Result<Boolean> save(@Validated @RequestBody PluginStatSaveParameter parameter) {
        pluginStatService.save(parameter);
        return new Result<>(true);
    }


    @DeleteMapping("/delete")
    @ApiOperation("删除插件统计")
    @WebLog(target = "PluginStat")
    public Result<Boolean> delete(@Validated @RequestBody PluginStatDeleteParameter parameter) {
        pluginStatService.delete(parameter);
        return new Result<>(true);
    }

}
