package com.chia.multienty.core.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.chia.multienty.core.annotation.WebLog;
import com.chia.multienty.core.domain.basic.Result;
import com.chia.multienty.core.domain.dto.TenantSecretDTO;
import com.chia.multienty.core.parameter.tenant.*;
import com.chia.multienty.core.service.TenantSecretService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
/**
 * <p>
 * 租户密钥 服务
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-03-27
 */
@RestController
@Validated
@RequestMapping("/tenant-secret")
@RequiredArgsConstructor
@Api(tags = "租户密钥")
public class TenantSecretController {
    private final TenantSecretService tenantSecretService;

    @PostMapping("/detail")
    @ApiOperation("获取租户密钥详情")
    public Result<TenantSecretDTO> getDetail(@Validated @RequestBody TenantSecretDetailGetParameter parameter) {
        TenantSecretDTO detail = tenantSecretService.getDetail(parameter);
        return new Result<>(detail);
    }

    @PostMapping("/page")
    @ApiOperation("获取租户密钥分页列表")
    public Result<IPage<TenantSecretDTO>> getPage(@Validated @RequestBody TenantSecretPageGetParameter parameter) {
        IPage<TenantSecretDTO> page = tenantSecretService.getPage(parameter);
        return new Result<>(page);
    }

    @PostMapping("/update")
    @ApiOperation("更新租户密钥")
    @WebLog(target = "TenantSecret")
    public Result<Boolean> update(@Validated @RequestBody TenantSecretUpdateParameter parameter) {
        tenantSecretService.update(parameter);
        return new Result<>(true);
    }

    @PostMapping("/save")
    @ApiOperation("保存租户密钥")
    @WebLog(target = "TenantSecret")
    public Result<Boolean> save(@Validated @RequestBody TenantSecretSaveParameter parameter) {
        tenantSecretService.save(parameter);
        return new Result<>(true);
    }

    @PostMapping("/enable")
    @ApiOperation("启用租户密钥")
    @WebLog(target = "TenantSecret")
    public Result<Boolean> enable(@Validated @RequestBody TenantSecretEnableParameter parameter) {
        tenantSecretService.enable(parameter);
        return new Result<>(true);
    }

    @PostMapping("/disable")
    @ApiOperation("禁用租户密钥")
    @WebLog(target = "TenantSecret")
    public Result<Boolean> disable(@Validated @RequestBody TenantSecretDisableParameter parameter) {
        tenantSecretService.disable(parameter);
        return new Result<>(true);
    }

    @DeleteMapping("/delete")
    @ApiOperation("删除租户密钥")
    @WebLog(target = "TenantSecret")
    public Result<Boolean> delete(@Validated @RequestBody TenantSecretDeleteParameter parameter) {
        tenantSecretService.delete(parameter);
        return new Result<>(true);
    }

}
