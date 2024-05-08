package com.chia.multienty.core.controller;


import com.chia.multienty.core.annotation.WebLog;
import com.chia.multienty.core.domain.basic.Result;
import com.chia.multienty.core.domain.dto.RolePermissionDTO;
import com.chia.multienty.core.service.RolePermissionService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.web.bind.annotation.RequestMapping;
import com.chia.multienty.core.parameter.user.RolePermissionDetailGetParameter;
import com.chia.multienty.core.parameter.user.RolePermissionPageGetParameter;
import com.chia.multienty.core.parameter.user.RolePermissionDeleteParameter;
import com.chia.multienty.core.parameter.user.RolePermissionSaveParameter;
import com.chia.multienty.core.parameter.user.RolePermissionUpdateParameter;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
/**
 * <p>
 * 账户角色关联前端控制器
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-01-10
 */
@RestController
@RequestMapping("/role-permission")
@RequiredArgsConstructor
@Api(tags = "账户角色关联前端控制器")
@ConditionalOnProperty(prefix = "spring.multienty", name = "user-module-enabled", havingValue = "true")
public class RolePermissionController {
    private final RolePermissionService rolePermissionService;

    @PostMapping("/detail")
    @ApiOperation("获取账户角色关联权限实体详情")
    public Result<RolePermissionDTO> getDetail(@RequestBody RolePermissionDetailGetParameter parameter) {
        RolePermissionDTO detail = rolePermissionService.getDetail(parameter);
        return new Result<>(detail);
    }

    @PostMapping("/page")
    @ApiOperation("获取账户角色关联权限实体分页列表")
    public Result<IPage<RolePermissionDTO>> getPage(@RequestBody RolePermissionPageGetParameter parameter) {
        IPage<RolePermissionDTO> page = rolePermissionService.getPage(parameter);
        return new Result<>(page);
    }

    @PostMapping("/update")
    @ApiOperation("更新账户角色关联权限实体")
    @WebLog(target = "RolePermission")
    public Result<Boolean> update(@RequestBody RolePermissionUpdateParameter parameter) {
        rolePermissionService.update(parameter);
        return new Result<>(true);
    }

    @PostMapping("/save")
    @ApiOperation("保存账户角色关联权限实体")
    @WebLog(target = "RolePermission")
    public Result<Boolean> save(@RequestBody RolePermissionSaveParameter parameter) {
        rolePermissionService.save(parameter);
        return new Result<>(true);
    }

    @DeleteMapping("/delete")
    @ApiOperation("删除账户角色关联权限实体")
    @WebLog(target = "RolePermission")
    public Result<Boolean> delete(@RequestBody RolePermissionDeleteParameter parameter) {
        rolePermissionService.delete(parameter);
        return new Result<>(true);
    }
}
