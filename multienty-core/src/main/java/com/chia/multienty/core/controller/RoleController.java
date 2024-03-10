package com.chia.multienty.core.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.chia.multienty.core.annotation.WebLog;
import com.chia.multienty.core.domain.basic.Result;
import com.chia.multienty.core.domain.dto.RoleDTO;
import com.chia.multienty.core.domain.enums.HttpResultEnum;
import com.chia.multienty.core.exception.KutaRuntimeException;
import com.chia.multienty.core.parameter.user.*;
import com.chia.multienty.core.service.RolePermissionService;
import com.chia.multienty.core.service.RoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 角色前端控制器
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-01-03
 */
@RestController
@RequestMapping("/role")
@Api(tags = "角色前端控制器")
@ConditionalOnProperty(prefix = "spring.multienty", name = "user-module-enabled", havingValue = "true")
public class RoleController {
    @Autowired
    private RoleService roleService;
    @Autowired
    private RolePermissionService rolePermissionService;

    @PostMapping("getList")
    @ApiOperation(value = "获取角色列表")
    public Result<IPage<RoleDTO>> getList(@RequestBody RoleListGetParameter parameter) {
        IPage<RoleDTO> list = roleService.getList(parameter);
        return new Result<>(list, HttpResultEnum.SUCCESS.getCode());
    }

    @PostMapping("save")
    @ApiOperation(value = "保存角色信息")
    @WebLog
    public Result<Boolean> save(@RequestBody RoleSaveParameter parameter) {
        Boolean result = roleService.save(parameter) == 1;
        return new Result<>(result, HttpResultEnum.SUCCESS);
    }

    @PostMapping("update")
    @ApiOperation(value = "更新角色信息")
    @WebLog
    public Result<Boolean> update(@RequestBody RoleUpdateParameter parameter) throws KutaRuntimeException {
        Boolean result = roleService.update(parameter) == 1;
        return new Result<>(result, HttpResultEnum.SUCCESS);
    }
    @PostMapping("delete")
    @ApiOperation(value = "删除角色信息")
    @WebLog
    public Result<Boolean> delete(@RequestBody RoleDeleteParameter parameter) throws KutaRuntimeException {
        Boolean result = roleService.deleteSafely(parameter.getRoleId());
        return new Result<>(result, HttpResultEnum.SUCCESS);
    }
    @PostMapping("enable")
    @ApiOperation(value = "启用角色")
    @WebLog
    public Result<Boolean> enable(@RequestBody RoleEnableParameter parameter) {
        Boolean result = roleService.enable(parameter.getRoleId());
        return new Result<>(result, HttpResultEnum.SUCCESS);
    }
    @PostMapping("disable")
    @ApiOperation(value = "禁用角色")
    @WebLog
    public Result<Boolean> disable(@RequestBody RoleDisableParameter parameter) {
        Boolean result = roleService.disable(parameter.getRoleId());
        return new Result<>(result, HttpResultEnum.SUCCESS);
    }
    @PostMapping("changePermissions")
    @ApiOperation(value = "分配权限")
    @WebLog
    public Result<Boolean> changePermissions(@RequestBody RolePermissionChangeParameter parameter) {
        boolean result = rolePermissionService.changePermissions(parameter);
        return new Result<>(result, HttpResultEnum.SUCCESS);
    }
}
