package com.chia.multienty.wechat.thirdparty.controller;

import com.chia.multienty.core.annotation.ValidateHeader;
import com.chia.multienty.core.domain.basic.Result;
import com.chia.multienty.core.domain.constants.MultientyHeaderConstants;
import com.chia.multienty.core.tools.MultientyContext;
import com.chia.multienty.wechat.thirdparty.parameter.baseinfo.*;
import com.chia.multienty.wechat.thirdparty.sdk.response.baseinfo.*;
import com.chia.multienty.wechat.thirdparty.service.WxTPBaseInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 * 微信第三方平台基础信息前端控制器
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-01-05
 */
@RestController
@RequestMapping("/wx/tp/base-info")
@RequiredArgsConstructor
@Api(tags = "微信第三方平台基础信息前端控制器")
public class WxTPBaseInfoController {
    private final WxTPBaseInfoService wxTPBaseInfoService;

    @ApiOperation("获取帐号基本信息")
    @PostMapping("getBaseInfo")
    @ValidateHeader(headNames = MultientyHeaderConstants.MPP_APP_ID_KEY)
    public Result<AccountBasicInfoGetResponse> getAccountBasicInfo() {
        AccountBasicInfoGetResponse response = wxTPBaseInfoService.getAccountBasicInfo(MultientyContext.getMppAppId());
        return new Result<>(response);
    }

    @ApiOperation("获取绑定的开放平台帐号")
    @PostMapping("getBindOpenAccount")
    @ValidateHeader(headNames = MultientyHeaderConstants.MPP_APP_ID_KEY)
    public Result<BindOpenAccountGetResponse> getBindOpenAccount() {
        BindOpenAccountGetResponse response = wxTPBaseInfoService.getBindOpenAccount(MultientyContext.getMppAppId());
        return new Result<>(response);
    }
    @ApiOperation("设置头像")
    @PostMapping("setHeadImage")
    @ValidateHeader(headNames = MultientyHeaderConstants.MPP_APP_ID_KEY)
    public Result<HeadImageSetResponse> setHeadImage(MultipartFile file) {
        HeadImageSetResponse response = wxTPBaseInfoService.setHeadImage(MultientyContext.getMppAppId(), file);
        return new Result<>(response);
    }
    @ApiOperation("微信登录")
    @PostMapping("login")
    @ValidateHeader(headNames = MultientyHeaderConstants.MPP_APP_ID_KEY)
    public Result<LoginResponse> login(@RequestBody LoginParameter parameter) {
        LoginResponse response = wxTPBaseInfoService.login(MultientyContext.getMppAppId(), parameter.getJsCode());
        return new Result<>(response);
    }
    @ApiOperation("小程序名称检测")
    @PostMapping("checkNickName")
    @ValidateHeader(headNames = MultientyHeaderConstants.MPP_APP_ID_KEY)
    public Result<NickNameCheckResponse> checkNickName(@RequestBody NickNameCheckParameter parameter) {
        NickNameCheckResponse response = wxTPBaseInfoService.checkNickName(MultientyContext.getMppAppId(),
                parameter.getNickName());
        return new Result<>(response);
    }
    @ApiOperation("设置小程序名称")
    @PostMapping("setNickName")
    @ValidateHeader(headNames = MultientyHeaderConstants.MPP_APP_ID_KEY)
    public Result<NickNameSetResponse> setNickName(@RequestBody NickNameSetParameter parameter) {
        NickNameSetResponse response = wxTPBaseInfoService.setNickName(MultientyContext.getMppAppId(),
                parameter.getNickName(), parameter.getMediaMap());
        return new Result<>(response);
    }
    @ApiOperation("查询小程序名称审核状态")
    @PostMapping("getNickNameStatus")
    @ValidateHeader(headNames = MultientyHeaderConstants.MPP_APP_ID_KEY)
    public Result<NickNameStatusGetResponse> getNickNameStatus(@RequestBody NickNameStatusGetParameter parameter) {
        NickNameStatusGetResponse response = wxTPBaseInfoService.getNickNameStatus(MultientyContext.getMppAppId(),
                parameter.getAuditId());
        return new Result<>(response);
    }
    @ApiOperation("获取搜索状态")
    @PostMapping("getSearchStatus")
    @ValidateHeader(headNames = MultientyHeaderConstants.MPP_APP_ID_KEY)
    public Result<SearchStatusGetResponse> getSearchStatus() {
        SearchStatusGetResponse response = wxTPBaseInfoService.getSearchStatus(MultientyContext.getMppAppId());
        return new Result<>(response);
    }
    @ApiOperation("设置搜索状态")
    @PostMapping("setSearchStatus")
    @ValidateHeader(headNames = MultientyHeaderConstants.MPP_APP_ID_KEY)
    public Result<SearchStatusSetResponse> setSearchStatus(@RequestBody SearchStatusSetParameter parameter) {
        SearchStatusSetResponse response = wxTPBaseInfoService.setSearchStatus(MultientyContext.getMppAppId(),
                parameter.getStatus());
        return new Result<>(response);
    }
    @ApiOperation("设置小程序介绍")
    @PostMapping("setSignature")
    @ValidateHeader(headNames = MultientyHeaderConstants.MPP_APP_ID_KEY)
    public Result<SignatureSetResponse> setSignature(@RequestBody SignatureSetParameter parameter) {
        SignatureSetResponse response = wxTPBaseInfoService.setSignature(MultientyContext.getMppAppId(),
                parameter.getSignature());
        return new Result<>(response);
    }
}
