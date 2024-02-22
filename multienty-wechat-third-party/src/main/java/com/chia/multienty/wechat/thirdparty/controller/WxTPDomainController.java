package com.chia.multienty.wechat.thirdparty.controller;

import com.chia.multienty.core.annotation.ValidateHeader;
import com.chia.multienty.core.domain.basic.Result;
import com.chia.multienty.core.domain.constants.MultiTenantHeaderConstants;
import com.chia.multienty.core.tools.MultiTenantContext;
import com.chia.multienty.wechat.thirdparty.parameter.domain.*;
import com.chia.multienty.wechat.thirdparty.sdk.response.domain.*;
import com.chia.multienty.wechat.thirdparty.service.WxTPDomainService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 微信第三方平台域名管理前端控制器
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-01-05
 */
@RestController
@RequestMapping("/wx/tp/domain")
@RequiredArgsConstructor
@Api(tags = "微信第三方平台域名管理前端控制器")
public class WxTPDomainController {
    private final WxTPDomainService wxTPDomainService;

    @ApiOperation("获取发布后生效业务域名列表")
    @PostMapping("getEffectiveJumpDomain")
    @ValidateHeader(headNames = MultiTenantHeaderConstants.MPP_APP_ID_KEY)
    public Result<EffectiveJumpDomainGetResponse> getEffectiveJumpDomain() {
        EffectiveJumpDomainGetResponse response = wxTPDomainService.getEffectiveJumpDomain(MultiTenantContext.getMppAppId());
        return new Result<>(response);
    }
    @ApiOperation("获取发布后生效服务器域名列表")
    @PostMapping("getEffectiveServerDomain")
    @ValidateHeader(headNames = MultiTenantHeaderConstants.MPP_APP_ID_KEY)
    public Result<EffectiveServerDomainGetResponse> getEffectiveServerDomain() {
        EffectiveServerDomainGetResponse response = wxTPDomainService.getEffectiveServerDomain(MultiTenantContext.getMppAppId());
        return new Result<>(response);
    }
    @ApiOperation("获取业务域名校验文件")
    @PostMapping("getJumpDomainConfirmFile")
    @ValidateHeader(headNames = MultiTenantHeaderConstants.MPP_APP_ID_KEY)
    public Result<JumpDomainConfirmFileGetResponse> getJumpDomainConfirmFile() {
        JumpDomainConfirmFileGetResponse response = wxTPDomainService.getJumpDomainConfirmFile(MultiTenantContext.getMppAppId());
        return new Result<>(response);
    }
    @ApiOperation("快速配置小程序业务域名")
    @PostMapping("modifyJumpDomainDirectly")
    @ValidateHeader(headNames = MultiTenantHeaderConstants.MPP_APP_ID_KEY)
    public Result<JumpDomainModifyDirectlyResponse> modifyJumpDomainDirectly(@RequestBody JumpDomainModifyDirectlyParameter parameter){
        JumpDomainModifyDirectlyResponse response = wxTPDomainService.modifyJumpDomainDirectly(MultiTenantContext.getMppAppId(),
                parameter.getAction(), parameter.getWebViewDomains());
        return new Result<>(response);
    }
    @ApiOperation("配置小程序业务域名")
    @PostMapping("modifyJumpDomain")
    @ValidateHeader(headNames = MultiTenantHeaderConstants.MPP_APP_ID_KEY)
    public Result<JumpDomainModifyResponse> modifyJumpDomain(@RequestBody JumpDomainModifyParameter parameter) {
        JumpDomainModifyResponse response = wxTPDomainService.modifyJumpDomain(MultiTenantContext.getMppAppId(),
                parameter.getAction(),
                parameter.getWebViewDomains());
        return new Result<>(response);
    }
    @ApiOperation("快速配置小程序服务器域名")
    @PostMapping("modifyServerDomainDirectly")
    @ValidateHeader(headNames = MultiTenantHeaderConstants.MPP_APP_ID_KEY)
    public Result<ServerDomainModifyDirectlyResponse> modifyServerDomainDirectly(
           @RequestBody ServerDomainModifyDirectlyParameter parameter){
        ServerDomainModifyDirectlyResponse response = wxTPDomainService.modifyServerDomainDirectly(parameter);
        return new Result<>(response);
    }
    @ApiOperation("配置小程序服务器域名")
    @PostMapping("modifyServerDomain")
    @ValidateHeader(headNames = MultiTenantHeaderConstants.MPP_APP_ID_KEY)
    public Result<ServerDomainModifyResponse> modifyServerDomain(@RequestBody ServerDomainModifyParameter parameter) {
        ServerDomainModifyResponse response = wxTPDomainService.modifyServerDomain(parameter);
        return new Result<>(response);
    }
    @ApiOperation("获取第三方平台业务域名校验文件")
    @PostMapping("getThirdPartyJumpDomainConfirmFile")
    public Result<ThirdPartyJumpDomainConfirmFileGetResponse> getThirdPartyJumpDomainConfirmFile() {
        ThirdPartyJumpDomainConfirmFileGetResponse response = wxTPDomainService.getThirdPartyJumpDomainConfirmFile();
        return new Result<>(response);
    }
    @ApiOperation("设置第三方平台业务域名")
    @PostMapping("modifyThirdPartyJumpDomain")
    public Result<ThirdPartyJumpDomainModifyResponse> modifyThirdPartyJumpDomain(
           @RequestBody ThirdPartyJumpDomainModifyParameter parameter) {
        ThirdPartyJumpDomainModifyResponse response = wxTPDomainService.modifyThirdPartyJumpDomain(parameter);
        return new Result<>(response);
    }
    @ApiOperation("设置第三方平台服务器域名")
    @PostMapping("modifyThirdPartyServerDomain")
    public Result<ThirdPartyServerDomainModifyResponse> modifyThirdPartyServerDomain(
            @RequestBody ThirdPartyServerDomainModifyParameter parameter) {
        ThirdPartyServerDomainModifyResponse response = wxTPDomainService.modifyThirdPartyServerDomain(parameter);
        return new Result<>(response);
    }
}
