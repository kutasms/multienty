package com.chia.multienty.core.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.chia.multienty.core.annotation.WebLog;
import com.chia.multienty.core.domain.basic.Result;
import com.chia.multienty.core.domain.dto.PluginDependencyDTO;
import com.chia.multienty.core.parameter.plugin.*;
import com.chia.multienty.core.service.PluginDependencyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
/**
 * <p>
 * 插件依赖 服务
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-03-16
 */
@RestController
@Validated
@RequestMapping("/plugin-dependency")
@RequiredArgsConstructor
@Api(tags = "插件依赖")
public class PluginDependencyController {
    private final PluginDependencyService pluginDependencyService;

    @PostMapping("/detail")
    @ApiOperation("获取插件依赖详情")
    public Result<PluginDependencyDTO> getDetail(@Validated @RequestBody PluginDependencyDetailGetParameter parameter) {
        PluginDependencyDTO detail = pluginDependencyService.getDetail(parameter);
        return new Result<>(detail);
    }

    @PostMapping("/page")
    @ApiOperation("获取插件依赖分页列表")
    public Result<IPage<PluginDependencyDTO>> getPage(@Validated @RequestBody PluginDependencyPageGetParameter parameter) {
        IPage<PluginDependencyDTO> page = pluginDependencyService.getPage(parameter);
        return new Result<>(page);
    }

    @PostMapping("/update")
    @ApiOperation("更新插件依赖")
    @WebLog(target = "PluginDependency")
    public Result<Boolean> update(@Validated @RequestBody PluginDependencyUpdateParameter parameter) {
        pluginDependencyService.update(parameter);
        return new Result<>(true);
    }

    @PostMapping("/save")
    @ApiOperation("保存插件依赖")
    @WebLog(target = "PluginDependency")
    public Result<Boolean> save(@Validated @RequestBody PluginDependencySaveParameter parameter) {
        pluginDependencyService.save(parameter);
        return new Result<>(true);
    }


    @DeleteMapping("/delete")
    @ApiOperation("删除插件依赖")
    @WebLog(target = "PluginDependency")
    public Result<Boolean> delete(@Validated @RequestBody PluginDependencyDeleteParameter parameter) {
        pluginDependencyService.delete(parameter);
        return new Result<>(true);
    }

}
