package com.chia.multienty.core.controller;


import com.chia.multienty.core.annotation.WebLog;
import com.chia.multienty.core.domain.basic.Result;
import com.chia.multienty.core.domain.dto.UserRoleDTO;
import com.chia.multienty.core.service.UserRoleService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.web.bind.annotation.RequestMapping;
import com.chia.multienty.core.parameter.user.UserRoleDetailGetParameter;
import com.chia.multienty.core.parameter.user.UserRolePageGetParameter;
import com.chia.multienty.core.parameter.user.UserRoleDeleteParameter;
import com.chia.multienty.core.parameter.user.UserRoleSaveParameter;
import com.chia.multienty.core.parameter.user.UserRoleUpdateParameter;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
/**
 * <p>
 * 用户角色关联前端控制器
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-01-10
 */
@RestController
@RequestMapping("/user-role")
@RequiredArgsConstructor
@Api(tags = "用户角色关联前端控制器")
@ConditionalOnProperty(prefix = "spring.multienty", name = "user-module-enabled", havingValue = "true")
public class UserRoleController {
    private final UserRoleService userRoleService;

    @PostMapping("/detail")
    @ApiOperation("获取账户关联角色信息表详情")
    public Result<UserRoleDTO> getDetail(@RequestBody UserRoleDetailGetParameter parameter) {
        UserRoleDTO detail = userRoleService.getDetail(parameter);
        return new Result<>(detail);
    }

    @PostMapping("/page")
    @ApiOperation("获取账户关联角色信息表分页列表")
    public Result<IPage<UserRoleDTO>> getPage(@RequestBody UserRolePageGetParameter parameter) {
        IPage<UserRoleDTO> page = userRoleService.getPage(parameter);
        return new Result<>(page);
    }

    @PostMapping("/update")
    @ApiOperation("更新账户关联角色信息表")
    @WebLog(target = "UserRole")
    public Result<Boolean> update(@RequestBody UserRoleUpdateParameter parameter) {
        userRoleService.update(parameter);
        return new Result<>(true);
    }

    @PostMapping("/save")
    @ApiOperation("保存账户关联角色信息表")
    @WebLog(target = "UserRole")
    public Result<Boolean> save(@RequestBody UserRoleSaveParameter parameter) {
        userRoleService.save(parameter);
        return new Result<>(true);
    }

    @DeleteMapping("/delete")
    @ApiOperation("删除账户关联角色信息表")
    @WebLog(target = "UserRole")
    public Result<Boolean> delete(@RequestBody UserRoleDeleteParameter parameter) {
        userRoleService.delete(parameter);
        return new Result<>(true);
    }
}
