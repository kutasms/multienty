package com.chia.multienty.core.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.chia.multienty.core.annotation.WebLog;
import com.chia.multienty.core.domain.basic.Result;
import com.chia.multienty.core.domain.dto.PublicKeyDTO;
import com.chia.multienty.core.domain.dto.UserDTO;
import com.chia.multienty.core.domain.enums.HttpResultEnum;
import com.chia.multienty.core.domain.vo.LoggedUserVO;
import com.chia.multienty.core.exception.KutaRuntimeException;
import com.chia.multienty.core.parameter.BlankParameter;
import com.chia.multienty.core.parameter.user.*;
import com.chia.multienty.core.properties.yaml.YamlMultientyProperties;
import com.chia.multienty.core.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * <p>
 * 用户前端控制器
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-01-03
 */
@RestController
@RequestMapping("/user")
@Api(tags = "用户前端控制器")
@RequiredArgsConstructor
@ConditionalOnProperty(prefix = "spring.multienty", name = "user-module-enabled", havingValue = "true")
public class UserController {

    private final UserService userService;

    private final YamlMultientyProperties multientyProperties;

    @PostMapping("publicKey")
    @ApiOperation(value = "获取公钥")
    public Result<PublicKeyDTO> getPublicKey(@RequestBody BlankParameter parameter, HttpServletRequest request) {
        PublicKeyDTO dto = userService.getPublicKey();
        return new Result<>(dto, HttpResultEnum.SUCCESS.getCode());
    }


    @PostMapping("getInfo")
    @ApiOperation(value = "获取用户信息")
    public Result<LoggedUserVO> getInfo(HttpServletRequest request) throws KutaRuntimeException, IOException {
        LoggedUserVO vo = userService.getUserInfo();
        return new Result<>(vo, HttpResultEnum.SUCCESS);
    }

    @PostMapping("getList")
    @ApiOperation(value = "获取用户分页列表")
    public Result<IPage<UserDTO>> getList(@RequestBody UserListGetParameter parameter) {
        IPage<UserDTO> list = userService.getList(parameter);
        return new Result<>(list);
    }
    @PostMapping("save")
    @ApiOperation(value = "保存用户")
    @WebLog
    public Result<Boolean> save(@RequestBody UserSaveParameter parameter) {
        Boolean result = userService.save(parameter) == 1;
        return new Result<>(result, HttpResultEnum.SUCCESS);
    }

    @PostMapping("update")
    @ApiOperation(value = "更新用户")
    @WebLog
    public Result<Boolean> update(@RequestBody UserUpdateParameter parameter) {
        Boolean result = userService.update(parameter) == 1;
        return new Result<>(result, HttpResultEnum.SUCCESS);
    }
    @PostMapping("delete")
    @ApiOperation(value = "删除用户")
    @WebLog
    public Result<Boolean> delete(@RequestBody UserDeleteParameter parameter) throws KutaRuntimeException {
        Boolean result = userService.deleteSafely(parameter.getUserId()) == 1;
        return new Result<>(result, HttpResultEnum.SUCCESS);
    }
    @PostMapping("enable")
    @ApiOperation(value = "启用用户")
    @WebLog
    public Result<Boolean> enable(@RequestBody UserEnableParameter parameter) {
        Boolean result = userService.enable(parameter.getUserId());
        return new Result<>(result, HttpResultEnum.SUCCESS);
    }
    @PostMapping("disable")
    @ApiOperation(value = "禁用用户")
    @WebLog
    public Result<Boolean> disable(@RequestBody UserDisableParameter parameter) {
        Boolean result = userService.disable(parameter.getUserId());
        return new Result<>(result, HttpResultEnum.SUCCESS);
    }
}
