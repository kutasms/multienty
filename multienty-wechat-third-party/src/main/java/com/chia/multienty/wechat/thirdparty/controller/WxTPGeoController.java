package com.chia.multienty.wechat.thirdparty.controller;

import com.chia.multienty.core.annotation.ValidateHeader;
import com.chia.multienty.core.domain.basic.Result;
import com.chia.multienty.core.domain.constants.MultiTenantHeaderConstants;
import com.chia.multienty.core.tools.MultiTenantContext;
import com.chia.multienty.wechat.thirdparty.parameter.geo.PrivacyInterfaceApplyParameter;
import com.chia.multienty.wechat.thirdparty.sdk.response.geo.PrivacyInterfaceApplyResponse;
import com.chia.multienty.wechat.thirdparty.sdk.response.geo.PrivacyInterfaceGetResponse;
import com.chia.multienty.wechat.thirdparty.service.WxTPGeoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 微信第三方平台地理信息管理前端控制器
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-01-05
 */
@RestController
@RequestMapping("/wx/tp/geo")
@RequiredArgsConstructor
@Api(tags = "微信第三方平台地理信息管理前端控制器")
public class WxTPGeoController {
    private final WxTPGeoService wxTPGeoService;
    @ApiOperation("申请地理位置接口")
    @PostMapping("applyPrivacyInterface")
    @ValidateHeader(headNames = MultiTenantHeaderConstants.MPP_APP_ID_KEY)
    public Result<PrivacyInterfaceApplyResponse> applyPrivacyInterface(
            @RequestBody PrivacyInterfaceApplyParameter parameter){
        PrivacyInterfaceApplyResponse response = wxTPGeoService.applyPrivacyInterface(parameter);
        return new Result<>(response);
    }
    @ApiOperation("获取地理位置接口列表")
    @PostMapping("getPrivacyInterface")
    @ValidateHeader(headNames = MultiTenantHeaderConstants.MPP_APP_ID_KEY)
    public Result<PrivacyInterfaceGetResponse> getPrivacyInterface() {
        PrivacyInterfaceGetResponse response = wxTPGeoService.getPrivacyInterface(MultiTenantContext.getMppAppId());
        return new Result<>(response);
    }
}
