package com.chia.multienty.wechat.thirdparty.controller;

import com.chia.multienty.core.annotation.ValidateHeader;
import com.chia.multienty.core.domain.basic.Result;
import com.chia.multienty.core.domain.constants.MultiTenantHeaderConstants;
import com.chia.multienty.core.tools.MultiTenantContext;
import com.chia.multienty.wechat.thirdparty.sdk.response.express.MsgPluginApplyResponse;
import com.chia.multienty.wechat.thirdparty.sdk.response.express.QueryPluginApplyResponse;
import com.chia.multienty.wechat.thirdparty.sdk.response.express.ReturnPluginApplyResponse;
import com.chia.multienty.wechat.thirdparty.service.WxTPExpressService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 微信第三方平台物流管理前端控制器
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-01-05
 */
@RestController
@RequestMapping("/wx/tp/express")
@RequiredArgsConstructor
@Api(tags = "微信第三方平台物流管理前端控制器")
public class WxTPExpressController {
    private final WxTPExpressService wxTPExpressService;

    @ApiOperation("申请开通物流消息")
    @PostMapping("applyMsgPlugin")
    @ValidateHeader(headNames = MultiTenantHeaderConstants.MPP_APP_ID_KEY)
    public Result<MsgPluginApplyResponse> applyMsgPlugin() {
        MsgPluginApplyResponse response = wxTPExpressService.applyMsgPlugin(MultiTenantContext.getMppAppId());
        return new Result<>(response);
    }
    @ApiOperation("申请开通物流查询组件响应数据")
    @PostMapping("applyQueryPlugin")
    @ValidateHeader(headNames = MultiTenantHeaderConstants.MPP_APP_ID_KEY)
    public Result<QueryPluginApplyResponse> applyQueryPlugin() {
        QueryPluginApplyResponse response = wxTPExpressService.applyQueryPlugin(MultiTenantContext.getMppAppId());
        return new Result<>(response);
    }
    @ApiOperation("申请开通物流退货组件")
    @PostMapping("applyReturnPlugin")
    @ValidateHeader(headNames = MultiTenantHeaderConstants.MPP_APP_ID_KEY)
    public Result<ReturnPluginApplyResponse> applyReturnPlugin() {
        ReturnPluginApplyResponse response = wxTPExpressService.applyReturnPlugin(MultiTenantContext.getMppAppId());
        return new Result<>(response);
    }
}
