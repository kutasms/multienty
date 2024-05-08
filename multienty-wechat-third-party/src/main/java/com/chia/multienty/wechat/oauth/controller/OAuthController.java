package com.chia.multienty.wechat.oauth.controller;

import com.chia.multienty.core.domain.basic.Result;
import com.chia.multienty.wechat.oauth.define.WechatOAuthParameter;
import com.chia.multienty.wechat.oauth.define.WechatOAuthResult;
import com.chia.multienty.wechat.oauth.service.OAuthService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 微信OAuth授权
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-01-05
 */
@RestController
@RequestMapping("/wx/pc/oauth")
@RequiredArgsConstructor
@Api(tags = "微信第三方平台基础信息前端控制器")
public class OAuthController {
    private final OAuthService oAuthService;

    @ApiOperation("获取权限令牌")
    @PostMapping("getAccessToken")
    public Result<WechatOAuthResult> getAccessToken(@RequestBody WechatOAuthParameter parameter) {
        WechatOAuthResult result = oAuthService.getAccessToken(parameter.getCode());
        return new Result<>(result);
    }
    @ApiOperation("获取openId")
    @PostMapping("getOpenId")
    public Result<String> getOpenId() {
        WechatOAuthResult result = oAuthService.getAccessToken();
        if(result != null) {
            return new Result<>(result.getOpenId());
        }
        return new Result<>();
    }
}
