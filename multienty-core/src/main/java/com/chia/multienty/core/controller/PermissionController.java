package com.chia.multienty.core.controller;


import com.chia.multienty.core.annotation.WebLog;
import com.chia.multienty.core.domain.basic.Result;
import com.chia.multienty.core.domain.dto.PermissionDTO;
import com.chia.multienty.core.service.PermissionService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.web.bind.annotation.RequestMapping;
import com.chia.multienty.core.parameter.user.PermissionDetailGetParameter;
import com.chia.multienty.core.parameter.user.PermissionPageGetParameter;
import com.chia.multienty.core.parameter.user.PermissionDeleteParameter;
import com.chia.multienty.core.parameter.user.PermissionSaveParameter;
import com.chia.multienty.core.parameter.user.PermissionUpdateParameter;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
/**
 * <p>
 * 权限前端控制器
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-01-10
 */
@RestController
@RequestMapping("/permission")
@RequiredArgsConstructor
@Api(tags = "权限前端控制器")
@ConditionalOnProperty(prefix = "spring.multienty", name = "user-module-enabled", havingValue = "true")
public class PermissionController {
    private final PermissionService permissionService;

    @PostMapping("/detail")
    @ApiOperation("获取权限菜单信息详情")
    public Result<PermissionDTO> getDetail(@RequestBody PermissionDetailGetParameter parameter) {
        PermissionDTO detail = permissionService.getDetail(parameter);
        return new Result<>(detail);
    }

    @PostMapping("/page")
    @ApiOperation("获取权限菜单信息分页列表")
    public Result<IPage<PermissionDTO>> getPage(@RequestBody PermissionPageGetParameter parameter) {
        IPage<PermissionDTO> page = permissionService.getPage(parameter);
        return new Result<>(page);
    }

    @PostMapping("/update")
    @ApiOperation("更新权限菜单信息")
    @WebLog
    public Result<Boolean> update(@RequestBody PermissionUpdateParameter parameter) {
        permissionService.update(parameter);
        return new Result<>(true);
    }

    @PostMapping("/save")
    @ApiOperation("保存权限菜单信息")
    @WebLog
    public Result<Boolean> save(@RequestBody PermissionSaveParameter parameter) {
        permissionService.save(parameter);
        return new Result<>(true);
    }

    @DeleteMapping("/delete")
    @ApiOperation("删除权限菜单信息")
    @WebLog
    public Result<Boolean> delete(@RequestBody PermissionDeleteParameter parameter) {
        permissionService.delete(parameter);
        return new Result<>(true);
    }
}
