package com.chia.multienty.wechat.thirdparty.controller;

import com.chia.multienty.core.annotation.ValidateHeader;
import com.chia.multienty.core.domain.basic.Result;
import com.chia.multienty.core.domain.constants.MultientyHeaderConstants;
import com.chia.multienty.core.tools.MultientyContext;
import com.chia.multienty.wechat.thirdparty.parameter.register.*;
import com.chia.multienty.wechat.thirdparty.sdk.response.register.*;
import com.chia.multienty.wechat.thirdparty.service.WxTPRegisterService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
    @ValidateHeader(headNames = MultientyHeaderConstants.MPP_APP_ID_KEY)
    public Result<BetaMiniProgramNickNameModifyResponse> modifyBetaMiniProgramNickName(
            @RequestBody  BetaMiniProgramNickNameModifyParameter parameter
    ) {
        BetaMiniProgramNickNameModifyResponse response = wxTPRegisterService.modifyBetaMiniProgramNickName(
                MultientyContext.getMppAppId(),
                parameter.getName());
        return new Result<>(response);
    }
    @ApiOperation("注册试用小程序")
    @PostMapping("registerBetaMiniProgram")
    public Result<BetaMiniProgramRegisterResponse> registerBetaMiniProgram(
            @RequestBody  BetaMiniProgramRegisterParameter parameter
    ) {
        BetaMiniProgramRegisterResponse response = wxTPRegisterService.registerBetaMiniProgram(parameter);
        return new Result<>(response);
    }
    @ApiOperation("试用小程序快速转正")
    @PostMapping("verifyBetaMiniProgram")
    @ValidateHeader(headNames = MultientyHeaderConstants.MPP_APP_ID_KEY)
    private Result<BetaMiniProgramVerifyResponse> verifyBetaMiniProgram(@RequestBody BetaMiniProgramVerifyParameter parameter) {
        BetaMiniProgramVerifyResponse response = wxTPRegisterService.verifyBetaMiniProgram(
                MultientyContext.getMppAppId(), parameter.getVerifyInfo());
        return new Result<>(response);
    }
    @ApiOperation("快速注册企业小程序")
    @PostMapping("registerEnterpriseMiniProgram")
    public Result<EnterpriseMiniProgramRegisterResponse> registerEnterpriseMiniProgram(
            @RequestBody EnterpriseMiniProgramRegisterParameter parameter) {
        EnterpriseMiniProgramRegisterResponse response = wxTPRegisterService.registerEnterpriseMiniProgram(parameter);
        return new Result<>(response);
    }
    @ApiOperation("复用公众号主体快速注册小程序请求")
    @PostMapping("registerMiniProgramByOfficialAccount")
    @ValidateHeader(headNames = MultientyHeaderConstants.MPP_APP_ID_KEY)
    public Result<MiniProgramRegisterByOfficialAccountResponse> registerMiniProgramByOfficialAccount(
            @RequestBody MiniProgramRegisterByOfficialAccountParameter parameter
    ) {
        MiniProgramRegisterByOfficialAccountResponse response = wxTPRegisterService
                .registerMiniProgramByOfficialAccount(MultientyContext.getMppAppId(), parameter.getTicket());
        return new Result<>(response);
    }
}
