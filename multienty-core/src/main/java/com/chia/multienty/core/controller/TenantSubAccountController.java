package com.chia.multienty.core.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.chia.multienty.core.annotation.WebLog;
import com.chia.multienty.core.domain.basic.Result;
import com.chia.multienty.core.domain.dto.TenantSubAccountDTO;
import com.chia.multienty.core.parameter.tenant.*;
import com.chia.multienty.core.service.TenantSubAccountService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
/**
 * <p>
 * 余额账单 服务
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-02-01
 */
@RestController
@RequestMapping("/tenant-sub-account")
@RequiredArgsConstructor
@Api(tags = "余额账单")
public class TenantSubAccountController {
    private final TenantSubAccountService tenantSubAccountService;

    @PostMapping("/detail")
    @ApiOperation("获取余额账单详情")
    public Result<TenantSubAccountDTO> getDetail(@RequestBody TenantSubAccountDetailGetParameter parameter) {
        TenantSubAccountDTO detail = tenantSubAccountService.getDetail(parameter);
        return new Result<>(detail);
    }

    @PostMapping("/page")
    @ApiOperation("获取余额账单分页列表")
    public Result<IPage<TenantSubAccountDTO>> getPage(@RequestBody TenantSubAccountPageGetParameter parameter) {
        IPage<TenantSubAccountDTO> page = tenantSubAccountService.getPage(parameter);
        return new Result<>(page);
    }

    @PostMapping("/update")
    @ApiOperation("更新余额账单")
    @WebLog(target = "TenantSubAccount")
    public Result<Boolean> update(@RequestBody TenantSubAccountUpdateParameter parameter) {
        tenantSubAccountService.update(parameter);
        return new Result<>(true);
    }

    @PostMapping("/save")
    @ApiOperation("保存余额账单")
    @WebLog(target = "TenantSubAccount")
    public Result<Boolean> save(@RequestBody TenantSubAccountSaveParameter parameter) {
        tenantSubAccountService.save(parameter);
        return new Result<>(true);
    }

    @PostMapping("/enable")
    @ApiOperation("启用余额账单")
    @WebLog(target = "TenantSubAccount")
    public Result<Boolean> enable(@RequestBody TenantSubAccountEnableParameter parameter) {
        tenantSubAccountService.enable(parameter);
        return new Result<>(true);
    }

    @PostMapping("/disable")
    @ApiOperation("禁用余额账单")
    @WebLog(target = "TenantSubAccount")
    public Result<Boolean> disable(@RequestBody TenantSubAccountDisableParameter parameter) {
        tenantSubAccountService.disable(parameter);
        return new Result<>(true);
    }

    @DeleteMapping("/delete")
    @ApiOperation("删除余额账单")
    @WebLog(target = "TenantSubAccount")
    public Result<Boolean> delete(@RequestBody TenantSubAccountDeleteParameter parameter) {
        tenantSubAccountService.delete(parameter);
        return new Result<>(true);
    }

}
