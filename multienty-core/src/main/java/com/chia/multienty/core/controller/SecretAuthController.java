package com.chia.multienty.core.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.chia.multienty.core.annotation.WebLog;
import com.chia.multienty.core.domain.basic.Result;
import com.chia.multienty.core.domain.dto.SecretAuthDTO;
import com.chia.multienty.core.parameter.tenant.*;
import com.chia.multienty.core.service.SecretAuthService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
/**
 * <p>
 * 密钥授权 服务
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-03-27
 */
@RestController
@Validated
@RequestMapping("/secret-auth")
@RequiredArgsConstructor
@Api(tags = "密钥授权")
public class SecretAuthController {
    private final SecretAuthService secretAuthService;

    @PostMapping("/detail")
    @ApiOperation("获取密钥授权详情")
    public Result<SecretAuthDTO> getDetail(@Validated @RequestBody SecretAuthDetailGetParameter parameter) {
        SecretAuthDTO detail = secretAuthService.getDetail(parameter);
        return new Result<>(detail);
    }

    @PostMapping("/page")
    @ApiOperation("获取密钥授权分页列表")
    public Result<IPage<SecretAuthDTO>> getPage(@Validated @RequestBody SecretAuthPageGetParameter parameter) {
        IPage<SecretAuthDTO> page = secretAuthService.getPage(parameter);
        return new Result<>(page);
    }

    @PostMapping("/update")
    @ApiOperation("更新密钥授权")
    @WebLog(target = "SecretAuth")
    public Result<Boolean> update(@Validated @RequestBody SecretAuthUpdateParameter parameter) {
        secretAuthService.update(parameter);
        return new Result<>(true);
    }

    @PostMapping("/save")
    @ApiOperation("保存密钥授权")
    @WebLog(target = "SecretAuth")
    public Result<Boolean> save(@Validated @RequestBody SecretAuthSaveParameter parameter) {
        secretAuthService.save(parameter);
        return new Result<>(true);
    }

    @PostMapping("/enable")
    @ApiOperation("启用密钥授权")
    @WebLog(target = "SecretAuth")
    public Result<Boolean> enable(@Validated @RequestBody SecretAuthEnableParameter parameter) {
        secretAuthService.enable(parameter);
        return new Result<>(true);
    }

    @PostMapping("/disable")
    @ApiOperation("禁用密钥授权")
    @WebLog(target = "SecretAuth")
    public Result<Boolean> disable(@Validated @RequestBody SecretAuthDisableParameter parameter) {
        secretAuthService.disable(parameter);
        return new Result<>(true);
    }

    @DeleteMapping("/delete")
    @ApiOperation("删除密钥授权")
    @WebLog(target = "SecretAuth")
    public Result<Boolean> delete(@Validated @RequestBody SecretAuthDeleteParameter parameter) {
        secretAuthService.delete(parameter);
        return new Result<>(true);
    }

}
