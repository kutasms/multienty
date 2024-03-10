package com.chia.multienty.core.controller;


import com.chia.multienty.core.annotation.WebLog;
import com.chia.multienty.core.domain.basic.Result;
import com.chia.multienty.core.domain.dto.JsonConfigDTO;
import com.chia.multienty.core.service.JsonConfigService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.web.bind.annotation.RequestMapping;
import com.chia.multienty.core.parameter.base.JsonConfigDetailGetParameter;
import com.chia.multienty.core.parameter.base.JsonConfigPageGetParameter;
import com.chia.multienty.core.parameter.base.JsonConfigDeleteParameter;
import com.chia.multienty.core.parameter.base.JsonConfigSaveParameter;
import com.chia.multienty.core.parameter.base.JsonConfigUpdateParameter;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
/**
 * <p>
 * Json配置前端控制器
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-01-10
 */
@RestController
@RequestMapping("/json-config")
@RequiredArgsConstructor
@Api(tags = "Json配置前端控制器")
@ConditionalOnProperty(prefix = "spring.multienty", name = "base-module-enabled", havingValue = "true")
public class JsonConfigController {
    private final JsonConfigService jsonConfigService;

    @PostMapping("/detail")
    @ApiOperation("获取Json配置详情")
    public Result<JsonConfigDTO> getDetail(@RequestBody JsonConfigDetailGetParameter parameter) {
        JsonConfigDTO detail = jsonConfigService.getDetail(parameter);
        return new Result<>(detail);
    }

    @PostMapping("/page")
    @ApiOperation("获取Json配置分页列表")
    public Result<IPage<JsonConfigDTO>> getPage(@RequestBody JsonConfigPageGetParameter parameter) {
        IPage<JsonConfigDTO> page = jsonConfigService.getPage(parameter);
        return new Result<>(page);
    }

    @PostMapping("/update")
    @ApiOperation("更新Json配置")
    @WebLog
    public Result<Boolean> update(@RequestBody JsonConfigUpdateParameter parameter) {
        jsonConfigService.update(parameter);
        return new Result<>(true);
    }

    @PostMapping("/save")
    @ApiOperation("保存Json配置")
    @WebLog
    public Result<Boolean> save(@RequestBody JsonConfigSaveParameter parameter) {
        jsonConfigService.save(parameter);
        return new Result<>(true);
    }

    @DeleteMapping("/delete")
    @ApiOperation("删除Json配置")
    @WebLog
    public Result<Boolean> delete(@RequestBody JsonConfigDeleteParameter parameter) {
        jsonConfigService.delete(parameter);
        return new Result<>(true);
    }
}
