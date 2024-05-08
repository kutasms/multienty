package com.chia.multienty.core.controller;


import com.chia.multienty.core.annotation.WebLog;
import com.chia.multienty.core.domain.basic.Result;
import com.chia.multienty.core.domain.dto.TenantRoleDTO;
import com.chia.multienty.core.service.TenantRoleService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.web.bind.annotation.RequestMapping;
import com.chia.multienty.core.parameter.tenant.TenantRoleDetailGetParameter;
import com.chia.multienty.core.parameter.tenant.TenantRolePageGetParameter;
import com.chia.multienty.core.parameter.tenant.TenantRoleDeleteParameter;
import com.chia.multienty.core.parameter.tenant.TenantRoleSaveParameter;
import com.chia.multienty.core.parameter.tenant.TenantRoleUpdateParameter;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
/**
 * <p>
 * 租户角色关联 服务
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-01-21
 */
@RestController
@RequestMapping("/tenant-role")
@RequiredArgsConstructor
@Api(tags = "租户角色关联")
@ConditionalOnProperty(prefix = "spring.multienty", name = "user-module-enabled", havingValue = "true")
public class TenantRoleController {
    private final TenantRoleService tenantRoleService;

    @PostMapping("/detail")
    @ApiOperation("获取租户角色关联详情")
    public Result<TenantRoleDTO> getDetail(@RequestBody TenantRoleDetailGetParameter parameter) {
        TenantRoleDTO detail = tenantRoleService.getDetail(parameter);
        return new Result<>(detail);
    }

    @PostMapping("/page")
    @ApiOperation("获取租户角色关联分页列表")
    public Result<IPage<TenantRoleDTO>> getPage(@RequestBody TenantRolePageGetParameter parameter) {
        IPage<TenantRoleDTO> page = tenantRoleService.getPage(parameter);
        return new Result<>(page);
    }

    @PostMapping("/update")
    @ApiOperation("更新租户角色关联")
    @WebLog(target = "TenantRole")
    public Result<Boolean> update(@RequestBody TenantRoleUpdateParameter parameter) {
        tenantRoleService.update(parameter);
        return new Result<>(true);
    }

    @PostMapping("/save")
    @ApiOperation("保存租户角色关联")
    @WebLog(target = "TenantRole")
    public Result<Boolean> save(@RequestBody TenantRoleSaveParameter parameter) {
        tenantRoleService.save(parameter);
        return new Result<>(true);
    }


    @DeleteMapping("/delete")
    @ApiOperation("删除租户角色关联")
    @WebLog(target = "TenantRole")
    public Result<Boolean> delete(@RequestBody TenantRoleDeleteParameter parameter) {
        tenantRoleService.delete(parameter);
        return new Result<>(true);
    }

}
