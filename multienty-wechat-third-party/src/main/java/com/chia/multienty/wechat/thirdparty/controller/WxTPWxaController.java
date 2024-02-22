package com.chia.multienty.wechat.thirdparty.controller;

import com.chia.multienty.core.annotation.ValidateHeader;
import com.chia.multienty.core.domain.basic.Result;
import com.chia.multienty.core.domain.constants.MultiTenantHeaderConstants;
import com.chia.multienty.core.tools.MultiTenantContext;
import com.chia.multienty.wechat.thirdparty.parameter.wxa.WxAuthParameter;
import com.chia.multienty.wechat.thirdparty.parameter.wxa.WxAuthProgressQueryParameter;
import com.chia.multienty.wechat.thirdparty.parameter.wxa.WxReAuthParameter;
import com.chia.multienty.wechat.thirdparty.sdk.response.wxa.WxAuthMaterialUploadResponse;
import com.chia.multienty.wechat.thirdparty.sdk.response.wxa.WxAuthProgressQueryResponse;
import com.chia.multienty.wechat.thirdparty.sdk.response.wxa.WxAuthResponse;
import com.chia.multienty.wechat.thirdparty.sdk.response.wxa.WxReAuthResponse;
import com.chia.multienty.wechat.thirdparty.service.WxTPWxaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 * 微信第三方平台认证前端控制器
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-01-05
 */
@RestController
@RequestMapping("/wx/tp/wxa")
@RequiredArgsConstructor
@Api(tags = "微信第三方平台认证前端控制器")
public class WxTPWxaController {
    private final WxTPWxaService wxTPWxaService;
    @ApiOperation("小程序认证上传补充材料")
    @PostMapping("uploadWxAuthMaterial")
    @ValidateHeader(headNames = MultiTenantHeaderConstants.MPP_APP_ID_KEY)
    public Result<WxAuthMaterialUploadResponse> uploadWxAuthMaterial(MultipartFile file) {
        WxAuthMaterialUploadResponse response = wxTPWxaService.uploadWxAuthMaterial(
                file, MultiTenantContext.getMppAppId());
        return new Result<>(response);
    }
    @ApiOperation("小程序认证进度查询")
    @PostMapping("queryWxAuthProgress")
    @ValidateHeader(headNames = MultiTenantHeaderConstants.MPP_APP_ID_KEY)
    public Result<WxAuthProgressQueryResponse> queryWxAuthProgress(WxAuthProgressQueryParameter parameter) {
        WxAuthProgressQueryResponse response = wxTPWxaService.queryWxAuthProgress(
                MultiTenantContext.getMppAppId(), parameter.getTaskId());
        return new Result<>(response);
    }
    @ApiOperation("小程序认证")
    @PostMapping("authMiniProgram")
    @ValidateHeader(headNames = MultiTenantHeaderConstants.MPP_APP_ID_KEY)
    public Result<WxAuthResponse> authMiniProgram(WxAuthParameter parameter) {
        WxAuthResponse response = wxTPWxaService.authMiniProgram(
                MultiTenantContext.getMppAppId(), parameter.getAuthData());
        return new Result<>(response);
    }
    @ApiOperation("小程序认证重新提审")
    @PostMapping("reAuthMiniProgram")
    @ValidateHeader(headNames = MultiTenantHeaderConstants.MPP_APP_ID_KEY)
    public Result<WxReAuthResponse> reAuthMiniProgram(WxReAuthParameter parameter) {
        WxReAuthResponse response = wxTPWxaService.reAuthMiniProgram(MultiTenantContext.getMppAppId(),
                parameter.getAuthData());
        return new Result<>(response);
    }
}
