package com.chia.multienty.core.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.chia.multienty.core.annotation.WebLog;
import com.chia.multienty.core.domain.basic.Result;
import com.chia.multienty.core.domain.dto.PublicKeyDTO;
import com.chia.multienty.core.domain.dto.TenantDTO;
import com.chia.multienty.core.domain.enums.HttpResultEnum;
import com.chia.multienty.core.domain.vo.LoggedUserVO;
import com.chia.multienty.core.domain.vo.LoginResult;
import com.chia.multienty.core.exception.KutaRuntimeException;
import com.chia.multienty.core.parameter.BlankParameter;
import com.chia.multienty.core.parameter.tenant.*;
import com.chia.multienty.core.parameter.user.LoginParameter;
import com.chia.multienty.core.parameter.user.LogoutParameter;
import com.chia.multienty.core.properties.yaml.YamlMultientyProperties;
import com.chia.multienty.core.service.TenantService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 租户前端控制器
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-01-10
 */
@RestController
@RequestMapping("/tenant")
@RequiredArgsConstructor
@Api(tags = "租户前端控制器")
@ConditionalOnProperty(prefix = "spring.multienty", name = "user-module-enabled", havingValue = "true")
public class TenantController {
    private final TenantService tenantService;
    private final YamlMultientyProperties multientyProperties;
    @PostMapping("publicKey")
    @ApiOperation(value = "获取公钥")
    public Result<PublicKeyDTO> getPublicKey(@RequestBody BlankParameter parameter, HttpServletRequest request) {
        PublicKeyDTO dto = tenantService.getPublicKey();
        return new Result<>(dto, HttpResultEnum.SUCCESS.getCode());
    }

    @PostMapping("login")
    @ApiOperation(value = "登录")
    @WebLog
    public Mono<Result<LoginResult>> login(@RequestBody LoginParameter parameter, HttpServletRequest request) throws java.lang.Exception {
        return tenantService.login(parameter);
    }

    @PostMapping("getInfo")
    @ApiOperation(value = "获取租户信息")
    public Result<LoggedUserVO> getInfo(HttpServletRequest request) throws KutaRuntimeException {
        LoggedUserVO vo = tenantService.getLoggedInfo();
        return new Result<>(vo, HttpResultEnum.SUCCESS);
    }

    @PostMapping("logout")
    @ApiOperation(value = "登出")
    @WebLog
    public Result logout(@RequestBody LogoutParameter parameter, HttpServletRequest request) {
        tenantService.logout(parameter);
        return new Result(HttpResultEnum.SUCCESS);
    }

    @PostMapping("/detail")
    @ApiOperation("获取租户详情")
    public Result<TenantDTO> getDetail(@RequestBody TenantDetailGetParameter parameter) {
        TenantDTO detail = tenantService.getDetail(parameter);
        return new Result<>(detail);
    }

    @PostMapping("/page")
    @ApiOperation("获取租户分页列表")
    public Result<IPage<TenantDTO>> getPage(@RequestBody TenantPageGetParameter parameter) {
        IPage<TenantDTO> page = tenantService.getPage(parameter);
        return new Result<>(page);
    }

    @PostMapping("/update")
    @ApiOperation("更新租户")
    @WebLog
    public Result<Boolean> update(@RequestBody TenantUpdateParameter parameter) {
        tenantService.update(parameter);
        return new Result<>(true);
    }

    @PostMapping("/save")
    @ApiOperation("保存租户")
    @WebLog
    public Result<Boolean> save(@RequestBody TenantSaveParameter parameter) {
        tenantService.save(parameter);
        return new Result<>(true);
    }

    @DeleteMapping("/delete")
    @ApiOperation("删除租户")
    @WebLog
    public Result<Boolean> delete(@RequestBody TenantDeleteParameter parameter) {
        tenantService.delete(parameter);
        return new Result<>(true);
    }
}
