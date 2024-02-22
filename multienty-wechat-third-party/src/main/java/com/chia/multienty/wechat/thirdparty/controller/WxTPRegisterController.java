package com.chia.multienty.wechat.thirdparty.controller;

import com.chia.multienty.core.annotation.ValidateHeader;
import com.chia.multienty.core.domain.basic.Result;
import com.chia.multienty.core.domain.constants.MultiTenantHeaderConstants;
import com.chia.multienty.core.tools.MultiTenantContext;
import com.chia.multienty.wechat.thirdparty.parameter.register.*;
import com.chia.multienty.wechat.thirdparty.sdk.response.register.*;
import com.chia.multienty.wechat.thirdparty.service.WxTPRegisterService;
import com.chia.multienty.wechat.thirdparty.parameter.register.*;
import com.chia.multienty.wechat.thirdparty.sdk.response.register.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 微信第三方平台代注册前端控制器
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-01-05
 */
@RestController
@RequestMapping("/wx/tp/register")
@RequiredArgsConstructor
@Api(tags = "微信第三方平台代注册前端控制器")
public class WxTPRegisterController {
    private final WxTPRegisterService wxTPRegisterService;

    @ApiOperation("修改试用小程序名称")
    @PostMapping("modifyBetaMiniProgramNickName")
    @ValidateHeader(headNames = MultiTenantHeaderConstants.MPP_APP_ID_KEY)
    public Result<BetaMiniProgramNickNameModifyResponse> modifyBetaMiniProgramNickName(
            BetaMiniProgramNickNameModifyParameter parameter
    ) {
        BetaMiniProgramNickNameModifyResponse response = wxTPRegisterService.modifyBetaMiniProgramNickName(
                MultiTenantContext.getMppAppId(),
                parameter.getName());
        return new Result<>(response);
    }
    @ApiOperation("注册试用小程序")
    @PostMapping("registerBetaMiniProgram")
    public Result<BetaMiniProgramRegisterResponse> registerBetaMiniProgram(
            BetaMiniProgramRegisterParameter parameter
    ) {
        BetaMiniProgramRegisterResponse response = wxTPRegisterService.registerBetaMiniProgram(parameter.getName(),
                parameter.getOpenId());
        return new Result<>(response);
    }
    @ApiOperation("试用小程序快速认证")
    @PostMapping("verifyBetaMiniProgram")
    @ValidateHeader(headNames = MultiTenantHeaderConstants.MPP_APP_ID_KEY)
    private Result<BetaMiniProgramVerifyResponse> verifyBetaMiniProgram(BetaMiniProgramVerifyParameter parameter) {
        BetaMiniProgramVerifyResponse response = wxTPRegisterService.verifyBetaMiniProgram(
                MultiTenantContext.getMppAppId(), parameter.getVerifyInfo());
        return new Result<>(response);
    }
    @ApiOperation("快速注册企业小程序")
    @PostMapping("registerEnterpriseMiniProgram")
    public Result<EnterpriseMiniProgramRegisterResponse> registerEnterpriseMiniProgram(
            EnterpriseMiniProgramRegisterParameter parameter) {
        EnterpriseMiniProgramRegisterResponse response = wxTPRegisterService.registerEnterpriseMiniProgram(parameter);
        return new Result<>(response);
    }
    @ApiOperation("复用公众号主体快速注册小程序请求")
    @PostMapping("registerMiniProgramByOfficialAccount")
    @ValidateHeader(headNames = MultiTenantHeaderConstants.MPP_APP_ID_KEY)
    public Result<MiniProgramRegisterByOfficialAccountResponse> registerMiniProgramByOfficialAccount(
            MiniProgramRegisterByOfficialAccountParameter parameter
    ) {
        MiniProgramRegisterByOfficialAccountResponse response = wxTPRegisterService
                .registerMiniProgramByOfficialAccount(MultiTenantContext.getMppAppId(), parameter.getTicket());
        return new Result<>(response);
    }
}
