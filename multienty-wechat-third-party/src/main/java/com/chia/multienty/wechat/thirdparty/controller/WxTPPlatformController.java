package com.chia.multienty.wechat.thirdparty.controller;

import com.chia.multienty.core.domain.basic.Result;
import com.chia.multienty.core.tools.MultiTenantContext;
import com.chia.multienty.wechat.thirdparty.parameter.platform.*;
import com.chia.multienty.wechat.thirdparty.sdk.response.platform.*;
import com.chia.multienty.wechat.thirdparty.service.WxTPPlatformService;
import com.chia.multienty.wechat.thirdparty.parameter.platform.*;
import com.chia.multienty.wechat.thirdparty.sdk.response.platform.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 微信第三方平台端管理前端控制器
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-01-05
 */
@RestController
@RequestMapping("/wx/tp/platform")
@RequiredArgsConstructor
@Api(tags = "微信第三方平台端管理前端控制器")
public class WxTPPlatformController {
    private final WxTPPlatformService wxTPPlatformService;

    @ApiOperation("使用授权码获取授权信息")
    @PostMapping("getAuthorizationInfoByAuth")
    public Result<AuthorizationInfoGetByAuthCodeResponse> getAuthorizationInfoByAuth(
            @RequestBody AuthorizationInfoGetByAuthCodeParameter parameter) {
        AuthorizationInfoGetByAuthCodeResponse response = wxTPPlatformService.getAuthorizationInfoByAuth(
                parameter.getAuthorizationCode());
        return new Result<>(response);
    }
    @ApiOperation("获取授权账号调用令牌")
    @PostMapping("getAuthorizerAccessToken")
    public Result<AuthorizerAccessTokenGetResponse> getAuthorizerAccessToken() {
        AuthorizerAccessTokenGetResponse response = wxTPPlatformService.getAuthorizerAccessToken(
                MultiTenantContext.getTenant().getTenantId(),
                MultiTenantContext.getMppAppId());
        return new Result<>(response);
    }
    @ApiOperation("拉取已授权的账号信息")
    @PostMapping("getAuthorizerInfo")
    public Result<AuthorizerInfoGetResponse> getAuthorizerInfo() {
        AuthorizerInfoGetResponse response = wxTPPlatformService.getAuthorizerInfo(MultiTenantContext.getMppAppId());
        return new Result<>(response);
    }
    @ApiOperation("拉取已授权的账号列表")
    @PostMapping("getAuthorizerList")
    public Result<AuthorizerListGetResponse> getAuthorizerList(AuthorizerListGetParameter parameter) {
        AuthorizerListGetResponse response = wxTPPlatformService.getAuthorizerList(parameter.getOffset(), parameter.getCount());
        return new Result<>(response);
    }
    @ApiOperation("获取授权方选项信息")
    @PostMapping("getAuthorizerOptionInfo")
    public Result<AuthorizerOptionInfoGetResponse> getAuthorizerOptionInfo(AuthorizerOptionInfoGetParameter parameter) {
        AuthorizerOptionInfoGetResponse response = wxTPPlatformService.getAuthorizerOptionInfo(parameter.getOptionName());
        return new Result<>(response);
    }
    @ApiOperation("设置授权方选项信息")
    @PostMapping("setAuthorizerOptionInfo")
    public Result<AuthorizerOptionInfoSetResponse> setAuthorizerOptionInfo(AuthorizerOptionInfoSetParameter parameter) {
        AuthorizerOptionInfoSetResponse response = wxTPPlatformService.setAuthorizerOptionInfo(
                parameter.getOptionName(), parameter.getOptionValue());
        return new Result<>(response);
    }
    @ApiOperation("获取商家刷新令牌")
    @PostMapping("getAuthorizerRefreshToken")
    public Result<AuthorizerRefreshTokenGetResponse> getAuthorizerRefreshToken(
            AuthorizerRefreshTokenGetParameter parameter) {
        AuthorizerRefreshTokenGetResponse response = wxTPPlatformService.getAuthorizerRefreshToken(
                parameter.getAuthorizationCode());
        return new Result<>(response);
    }
    @ApiOperation("获取预授权码")
    @PostMapping("getPreAuthCode")
    public Result<String> getPreAuthCode() {
        String preAuthCode = wxTPPlatformService.getPreAuthCode();
        return new Result<>(preAuthCode);
    }
    @ApiOperation("启动票据推送服务")
    @PostMapping("startPushTicket")
    public Result<PushTicketStartResponse> startPushTicket() {
        PushTicketStartResponse response = wxTPPlatformService.startPushTicket();
        return new Result<>(response);
    }
}
