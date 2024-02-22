package com.chia.multienty.wechat.thirdparty.controller;

import com.chia.multienty.core.annotation.ValidateHeader;
import com.chia.multienty.core.domain.basic.Result;
import com.chia.multienty.core.domain.constants.MultiTenantHeaderConstants;
import com.chia.multienty.core.domain.enums.HttpResultEnum;
import com.chia.multienty.core.exception.KutaRuntimeException;
import com.chia.multienty.core.tools.MultiTenantContext;
import com.chia.multienty.wechat.thirdparty.parameter.oa.*;
import com.chia.multienty.wechat.thirdparty.sdk.response.oa.*;
import com.chia.multienty.wechat.thirdparty.service.WxTPOfficialService;
import com.chia.multienty.wechat.thirdparty.parameter.oa.*;
import com.chia.multienty.wechat.thirdparty.sdk.request.oa.OfficialAccountAuthLinkGenerateRequest;
import com.chia.multienty.wechat.thirdparty.sdk.response.oa.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * <p>
 * 微信第三方平台公众号管理前端控制器
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-01-05
 */
@RestController
@RequestMapping("/wx/tp/oa")
@RequiredArgsConstructor
@Api(tags = "微信第三方平台公众号管理前端控制器")
public class WxTPOAController {
    private final WxTPOfficialService wxTPOfficialService;

    @ApiOperation("增加或修改二维码规则")
    @PostMapping("addJumpQRCode")
    @ValidateHeader(headNames = MultiTenantHeaderConstants.MPP_APP_ID_KEY)
    public Result<JumpQRCodeAddResponse> addJumpQRCode(JumpQRCodeAddParameter parameter) {
        JumpQRCodeAddResponse response = wxTPOfficialService.addJumpQRCode(parameter);
        return new Result<>(response);
    }
    @ApiOperation("删除已设置的二维码规则")
    @PostMapping("deleteJumpQRCode")
    @ValidateHeader(headNames = MultiTenantHeaderConstants.MPP_APP_ID_KEY)
    public Result<JumpQRCodeDeleteResponse> deleteJumpQRCode(JumpQRCodeDeleteParameter parameter) {
        JumpQRCodeDeleteResponse response = wxTPOfficialService.deleteJumpQRCode(MultiTenantContext.getMppAppId(),
                parameter.getPrefix());
        return new Result<>(response);
    }
    @ApiOperation("获取已设置的二维码规则")
    @PostMapping("getJumpQRCode")
    @ValidateHeader(headNames = MultiTenantHeaderConstants.MPP_APP_ID_KEY)
    public Result<JumpQRCodeGetResponse> getJumpQRCode(JumpQRCodeGetParameter parameter){
        JumpQRCodeGetResponse response = wxTPOfficialService.getJumpQRCode(parameter);
        return new Result<>(response);
    }
    @ApiOperation("发布已设置的二维码规则")
    @PostMapping("publishJumpQRCode")
    @ValidateHeader(headNames = MultiTenantHeaderConstants.MPP_APP_ID_KEY)
    public Result<JumpQRCodePublishResponse> publishJumpQRCode(JumpQRCodePublishParameter parameter) {
        JumpQRCodePublishResponse response = wxTPOfficialService.publishJumpQRCode(MultiTenantContext.getMppAppId(),
                parameter.getPrefix());
        return new Result<>(response);
    }
    @ApiOperation("获取可设置公众号列表")
    @PostMapping("getLinkForShow")
    @ValidateHeader(headNames = MultiTenantHeaderConstants.MPP_APP_ID_KEY)
    public Result<LinkForShowGetResponse> getLinkForShow(LinkForShowGetParameter parameter) {
        LinkForShowGetResponse response = wxTPOfficialService.getLinkForShow(MultiTenantContext.getMppAppId(),
                parameter.getPage(),
                parameter.getNum());
        return new Result<>(response);
    }
    @ApiOperation("获取公众号关联的小程序")
    @PostMapping("getLinkMiniProgram")
    @ValidateHeader(headNames = MultiTenantHeaderConstants.MPP_APP_ID_KEY)
    public Result<LinkMiniProgramGetResponse> getLinkMiniProgram() {
        LinkMiniProgramGetResponse response = wxTPOfficialService.getLinkMiniProgram(MultiTenantContext.getMppAppId());
        return new Result<>(response);
    }
    @ApiOperation("公众号关联小程序")
    @PostMapping("linkMiniProgram")
    @ValidateHeader(headNames = MultiTenantHeaderConstants.MPP_APP_ID_KEY)
    public Result<MiniProgramLinkResponse> linkMiniProgram(MiniProgramLinkParameter parameter){
        MiniProgramLinkResponse response = wxTPOfficialService.linkMiniProgram(parameter);
        return new Result<>(response);
    }
    @ApiOperation("公众号解除关联小程序")
    @PostMapping("unlinkMiniProgram")
    @ValidateHeader(headNames = MultiTenantHeaderConstants.MPP_APP_ID_KEY)
    public Result<MiniProgramUnlinkResponse> unlinkMiniProgram() {
        MiniProgramUnlinkResponse response = wxTPOfficialService.unlinkMiniProgram(MultiTenantContext.getMppAppId());
        return new Result<>(response);
    }
    @ApiOperation("获取已设置公众号信息")
    @PostMapping("getShowItem")
    @ValidateHeader(headNames = MultiTenantHeaderConstants.MPP_APP_ID_KEY)
    public Result<ShowItemGetResponse> getShowItem() {
        ShowItemGetResponse response = wxTPOfficialService.getShowItem(MultiTenantContext.getMppAppId());
        return new Result<>(response);
    }
    @ApiOperation("设置扫码关注的公众号")
    @PostMapping("setShowItem")
    @ValidateHeader(headNames = MultiTenantHeaderConstants.MPP_APP_ID_KEY)
    public Result<ShowItemSetResponse> setShowItem(ShowItemSetParameter parameter) {
        ShowItemSetResponse response = wxTPOfficialService.setShowItem(
                MultiTenantContext.getMppAppId(), parameter.getWxaSubscribeBizFlag());
        return new Result<>(response);
    }
    @ApiOperation("新增临时素材")
    @PostMapping("addTempAsset")
    @ValidateHeader(headNames = MultiTenantHeaderConstants.MPP_APP_ID_KEY)
    public Result<TempAssetAddResponse> addTempAsset(HttpServletRequest request, MultipartFile file) {
        Map<String, String[]> parameterMap = request.getParameterMap();
        String[] types = parameterMap.get("type");
        if(types.length != 1) {
            throw new KutaRuntimeException(HttpResultEnum.ARG_ERROR_PATTERN, "type");
        }
        String type = types[0];
        TempAssetAddResponse response = wxTPOfficialService.addTempAsset(MultiTenantContext.getMppAppId(),
                file, type);
        return new Result<>(response);
    }
    @ApiOperation("生成公众号授权链接")
    @PostMapping("generateOfficialAccountAuthLink")
    @ValidateHeader(headNames = MultiTenantHeaderConstants.MPP_APP_ID_KEY)
    public Result<OfficialAccountAuthLinkGenerateResponse> generateOfficialAccountAuthLink(
            OfficialAccountAuthLinkGenerateRequest request
    ) {
        OfficialAccountAuthLinkGenerateResponse response = wxTPOfficialService.generateOfficialAccountAuthLink(request);
        return new Result<>(response);
    }
}
