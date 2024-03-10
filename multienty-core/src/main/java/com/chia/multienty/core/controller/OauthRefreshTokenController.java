package com.chia.multienty.core.controller;


import com.chia.multienty.core.annotation.WebLog;
import com.chia.multienty.core.domain.basic.Result;
import com.chia.multienty.core.parameter.oauth.OauthRefreshTokenSaveParameter;
import com.chia.multienty.core.parameter.oauth.OauthRefreshTokenUpdateParameter;
import com.chia.multienty.core.service.OauthRefreshTokenService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
/**
 * <p>
 * 更新令牌 服务
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-03-04
 */
@RestController
@Validated
@RequestMapping("/oauth-refresh-token")
@RequiredArgsConstructor
@Api(tags = "更新令牌")
public class OauthRefreshTokenController {
    private final OauthRefreshTokenService oauthRefreshTokenService;



    @PostMapping("/update")
    @ApiOperation("更新更新令牌")
    @WebLog
    public Result<Boolean> update(@Validated @RequestBody OauthRefreshTokenUpdateParameter parameter) {
        oauthRefreshTokenService.update(parameter);
        return new Result<>(true);
    }

    @PostMapping("/save")
    @ApiOperation("保存更新令牌")
    @WebLog
    public Result<Boolean> save(@Validated @RequestBody OauthRefreshTokenSaveParameter parameter) {
        oauthRefreshTokenService.save(parameter);
        return new Result<>(true);
    }



}
