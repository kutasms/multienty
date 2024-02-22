package com.chia.multienty.wechat.thirdparty.controller;

import com.chia.multienty.core.annotation.ValidateHeader;
import com.chia.multienty.core.domain.basic.Result;
import com.chia.multienty.core.domain.constants.MultiTenantHeaderConstants;
import com.chia.multienty.core.tools.MultiTenantContext;
import com.chia.multienty.wechat.thirdparty.parameter.member.TesterBindParameter;
import com.chia.multienty.wechat.thirdparty.parameter.member.TesterGetParameter;
import com.chia.multienty.wechat.thirdparty.parameter.member.TesterUnbindParameter;
import com.chia.multienty.wechat.thirdparty.sdk.response.member.TesterBindResponse;
import com.chia.multienty.wechat.thirdparty.sdk.response.member.TesterGetResponse;
import com.chia.multienty.wechat.thirdparty.sdk.response.member.TesterUnbindResponse;
import com.chia.multienty.wechat.thirdparty.service.WxTPMemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 微信第三方平台成员管理前端控制器
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-01-05
 */
@RestController
@RequestMapping("/wx/tp/member")
@RequiredArgsConstructor
@Api(tags = "微信第三方平台成员管理前端控制器")
public class WxTPMemberController {
    private final WxTPMemberService wxTPMemberService;
    @ApiOperation("绑定体验者")
    @PostMapping("bindTester")
    @ValidateHeader(headNames = MultiTenantHeaderConstants.MPP_APP_ID_KEY)
    public Result<TesterBindResponse> bindTester(TesterBindParameter parameter) {
        TesterBindResponse response = wxTPMemberService.bindTester(MultiTenantContext.getMppAppId(), parameter.getWechatId());
        return new Result<>(response);
    }
    @ApiOperation("获取体验者列表")
    @PostMapping("getTester")
    @ValidateHeader(headNames = MultiTenantHeaderConstants.MPP_APP_ID_KEY)
    public Result<TesterGetResponse> getTester(TesterGetParameter parameter) {
        TesterGetResponse response = wxTPMemberService.getTester(MultiTenantContext.getMppAppId(), parameter.getAction());
        return new Result<>(response);
    }
    @ApiOperation("解除绑定体验者")
    @PostMapping("unbindTester")
    @ValidateHeader(headNames = MultiTenantHeaderConstants.MPP_APP_ID_KEY)
    public Result<TesterUnbindResponse> unbindTester(TesterUnbindParameter parameter) {
        TesterUnbindResponse response = wxTPMemberService.unbindTester(MultiTenantContext.getMppAppId(),
                parameter.getWechatId(),
                parameter.getUserStr());
        return new Result<>(response);
    }
}
