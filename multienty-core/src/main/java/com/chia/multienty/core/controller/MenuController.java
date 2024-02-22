package com.chia.multienty.core.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.chia.multienty.core.annotation.WebLog;
import com.chia.multienty.core.domain.basic.Result;
import com.chia.multienty.core.domain.constants.MultiTenantConstants;
import com.chia.multienty.core.domain.dto.PermissionDTO;
import com.chia.multienty.core.domain.dto.UserDTO;
import com.chia.multienty.core.domain.enums.HttpResultEnum;
import com.chia.multienty.core.domain.vo.permission.PermissionVO;
import com.chia.multienty.core.exception.KutaRuntimeException;
import com.chia.multienty.core.parameter.user.*;
import com.chia.multienty.core.service.PermissionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 菜单前端控制器
 */
@RestController
@RequestMapping("menu")
@Slf4j(topic = "MenuController")
@Api(tags = "菜单前端控制器")
@RequiredArgsConstructor
@ConditionalOnProperty(prefix = "spring.kuta.multi-tenant", name = "user-module-enabled", havingValue = "true")
public class MenuController {

    private final PermissionService permissionService;

    @PostMapping("navigate")
    @ApiOperation(value = "菜单导航")
    public Result<List<PermissionVO>> navigate(HttpServletRequest request) {
        UserDTO user = (UserDTO) request.getAttribute(MultiTenantConstants.CACHED_PLATFORM_USER_KEY);
        List<PermissionDTO> permissions = permissionService.getUserPermissions(user.getUserId(),
                MultiTenantConstants.APPLICATION_TYPE_PLATFORM.longValue());
        List<PermissionVO> vos = permissionService.getFormattedVO(permissions);
        return new Result<>(vos, HttpResultEnum.SUCCESS.getCode());
    }
    @PostMapping("getList")
    @ApiOperation(value = "获取菜单列表")
    public Result<IPage<PermissionDTO>> getList(@RequestBody TopPermissionListGetParameter parameter) {
        IPage<PermissionDTO> list = permissionService.getFormattedPlatformTopPermissions(parameter);
        return new Result<>(list, HttpResultEnum.SUCCESS.getCode());
    }

    @PostMapping("save")
    @ApiOperation(value = "保存菜单信息")
    @WebLog
    public Result<Boolean> save(@RequestBody PermissionSaveParameter parameter) {
        Boolean result = permissionService.save(parameter) == 1;
        return new Result<>(result, HttpResultEnum.SUCCESS);
    }

    @PostMapping("update")
    @ApiOperation(value = "更新菜单信息")
    @WebLog
    public Result<Boolean> update(@RequestBody PermissionUpdateParameter parameter) throws KutaRuntimeException {
        Boolean result = permissionService.update(parameter) == 1;
        return new Result<>(result, HttpResultEnum.SUCCESS);
    }
    @PostMapping("delete")
    @ApiOperation(value = "删除菜单信息")
    @WebLog
    public Result<Boolean> delete(@RequestBody PermissionDeleteParameter parameter) throws KutaRuntimeException {
        Boolean result = permissionService.removeById(parameter.getPermissionId());
        return new Result<>(result, HttpResultEnum.SUCCESS);
    }
    @PostMapping("enable")
    @ApiOperation(value = "启用菜单")
    @WebLog
    public Result<Boolean> enable(@RequestBody PermissionEnableParameter parameter) {
        Boolean result = permissionService.enable(parameter.getPermissionId());
        return new Result<>(result, HttpResultEnum.SUCCESS);
    }
    @PostMapping("disable")
    @ApiOperation(value = "禁用菜单")
    @WebLog
    public Result<Boolean> disable(@RequestBody PermissionDisableParameter parameter) {
        Boolean result = permissionService.disable(parameter.getPermissionId());
        return new Result<>(result, HttpResultEnum.SUCCESS);
    }
    @PostMapping("getFuncPermissions")
    @ApiOperation(value = "获取功能权限列表")
    public Result<List<PermissionDTO>> getFuncPermissions(@RequestBody FuncPermissionListGetParameter parameter) {
        List<PermissionDTO> permissions = permissionService.getFuncPermissions(parameter);
        return new Result<>(permissions);
    }
}
