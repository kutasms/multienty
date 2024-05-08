package com.chia.multienty.core.controller;


import com.chia.multienty.core.annotation.WebLog;
import com.chia.multienty.core.domain.basic.Result;
import com.chia.multienty.core.domain.dto.SettingDTO;
import com.chia.multienty.core.service.SettingService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.web.bind.annotation.RequestMapping;
import com.chia.multienty.core.parameter.base.SettingDetailGetParameter;
import com.chia.multienty.core.parameter.base.SettingPageGetParameter;
import com.chia.multienty.core.parameter.base.SettingDeleteParameter;
import com.chia.multienty.core.parameter.base.SettingSaveParameter;
import com.chia.multienty.core.parameter.base.SettingUpdateParameter;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
/**
 * <p>
 * 系统配置前端控制器
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-01-10
 */
@RestController
@RequestMapping("/setting")
@RequiredArgsConstructor
@Api(tags = "系统配置前端控制器")
@ConditionalOnProperty(prefix = "spring.multienty", name = "base-module-enabled", havingValue = "true")
public class SettingController {
    private final SettingService settingService;

    @PostMapping("/detail")
    @ApiOperation("获取系统配置详情")
    public Result<SettingDTO> getDetail(@RequestBody SettingDetailGetParameter parameter) {
        SettingDTO detail = settingService.getDetail(parameter);
        return new Result<>(detail);
    }

    @PostMapping("/page")
    @ApiOperation("获取系统配置分页列表")
    public Result<IPage<SettingDTO>> getPage(@RequestBody SettingPageGetParameter parameter) {
        IPage<SettingDTO> page = settingService.getPage(parameter);
        return new Result<>(page);
    }

    @PostMapping("/update")
    @ApiOperation("更新系统配置")
    @WebLog(target = "Setting")
    public Result<Boolean> update(@RequestBody SettingUpdateParameter parameter) {
        settingService.update(parameter);
        return new Result<>(true);
    }

    @PostMapping("/save")
    @ApiOperation("保存系统配置")
    @WebLog(target = "Setting")
    public Result<Boolean> save(@RequestBody SettingSaveParameter parameter) {
        settingService.save(parameter);
        return new Result<>(true);
    }

    @DeleteMapping("/delete")
    @ApiOperation("删除系统配置")
    @WebLog(target = "Setting")
    public Result<Boolean> delete(@RequestBody SettingDeleteParameter parameter) {
        settingService.delete(parameter);
        return new Result<>(true);
    }
}
