package com.chia.multienty.core.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.chia.multienty.core.annotation.WebLog;
import com.chia.multienty.core.domain.basic.Result;
import com.chia.multienty.core.domain.dto.PluginResDTO;
import com.chia.multienty.core.parameter.plugin.*;
import com.chia.multienty.core.service.PluginResService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
/**
 * <p>
 * 插件资源 服务
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-03-16
 */
@RestController
@Validated
@RequestMapping("/plugin-res")
@RequiredArgsConstructor
@Api(tags = "插件资源")
public class PluginResController {
    private final PluginResService pluginResService;

    @PostMapping("/detail")
    @ApiOperation("获取插件资源详情")
    public Result<PluginResDTO> getDetail(@Validated @RequestBody PluginResDetailGetParameter parameter) {
        PluginResDTO detail = pluginResService.getDetail(parameter);
        return new Result<>(detail);
    }

    @PostMapping("/page")
    @ApiOperation("获取插件资源分页列表")
    public Result<IPage<PluginResDTO>> getPage(@Validated @RequestBody PluginResPageGetParameter parameter) {
        IPage<PluginResDTO> page = pluginResService.getPage(parameter);
        return new Result<>(page);
    }

    @PostMapping("/update")
    @ApiOperation("更新插件资源")
    @WebLog(target = "PluginRes")
    public Result<Boolean> update(@Validated @RequestBody PluginResUpdateParameter parameter) {
        pluginResService.update(parameter);
        return new Result<>(true);
    }

    @PostMapping("/save")
    @ApiOperation("保存插件资源")
    @WebLog(target = "PluginRes")
    public Result<Boolean> save(@Validated @RequestBody PluginResSaveParameter parameter) {
        pluginResService.save(parameter);
        return new Result<>(true);
    }


    @DeleteMapping("/delete")
    @ApiOperation("删除插件资源")
    @WebLog(target = "PluginRes")
    public Result<Boolean> delete(@Validated @RequestBody PluginResDeleteParameter parameter) {
        pluginResService.delete(parameter);
        return new Result<>(true);
    }

}
