package com.chia.multienty.core.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.chia.multienty.core.annotation.WebLog;
import com.chia.multienty.core.domain.basic.Result;
import com.chia.multienty.core.domain.dto.TenantSubAccountRoleDTO;
import com.chia.multienty.core.parameter.tenant.*;
import com.chia.multienty.core.service.TenantSubAccountRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
/**
 * <p>
 * 租户子账号角色关联 服务
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-02-01
 */
@RestController
@RequestMapping("/tenant-sub-account-role")
@RequiredArgsConstructor
@Api(tags = "租户子账号角色关联")
public class TenantSubAccountRoleController {
    private final TenantSubAccountRoleService tenantSubAccountRoleService;

    @PostMapping("/detail")
    @ApiOperation("获取租户子账号角色关联详情")
    public Result<TenantSubAccountRoleDTO> getDetail(@RequestBody TenantSubAccountRoleDetailGetParameter parameter) {
        TenantSubAccountRoleDTO detail = tenantSubAccountRoleService.getDetail(parameter);
        return new Result<>(detail);
    }

    @PostMapping("/page")
    @ApiOperation("获取租户子账号角色关联分页列表")
    public Result<IPage<TenantSubAccountRoleDTO>> getPage(@RequestBody TenantSubAccountRolePageGetParameter parameter) {
        IPage<TenantSubAccountRoleDTO> page = tenantSubAccountRoleService.getPage(parameter);
        return new Result<>(page);
    }

    @PostMapping("/update")
    @ApiOperation("更新租户子账号角色关联")
    @WebLog(target = "TenantSubAccountRole")
    public Result<Boolean> update(@RequestBody TenantSubAccountRoleUpdateParameter parameter) {
        tenantSubAccountRoleService.update(parameter);
        return new Result<>(true);
    }

    @PostMapping("/save")
    @ApiOperation("保存租户子账号角色关联")
    @WebLog(target = "TenantSubAccountRole")
    public Result<Boolean> save(@RequestBody TenantSubAccountRoleSaveParameter parameter) {
        tenantSubAccountRoleService.save(parameter);
        return new Result<>(true);
    }


    @DeleteMapping("/delete")
    @ApiOperation("删除租户子账号角色关联")
    @WebLog(target = "TenantSubAccountRole")
    public Result<Boolean> delete(@RequestBody TenantSubAccountRoleDeleteParameter parameter) {
        tenantSubAccountRoleService.delete(parameter);
        return new Result<>(true);
    }

}
