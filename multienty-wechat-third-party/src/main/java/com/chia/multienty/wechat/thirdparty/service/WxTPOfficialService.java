package com.chia.multienty.wechat.thirdparty.service;

import com.chia.multienty.wechat.thirdparty.sdk.response.oa.*;
import com.chia.multienty.wechat.thirdparty.parameter.oa.JumpQRCodeAddParameter;
import com.chia.multienty.wechat.thirdparty.parameter.oa.JumpQRCodeGetParameter;
import com.chia.multienty.wechat.thirdparty.parameter.oa.MiniProgramLinkParameter;
import com.chia.multienty.wechat.thirdparty.sdk.request.oa.OfficialAccountAuthLinkGenerateRequest;
import com.chia.multienty.wechat.thirdparty.sdk.response.oa.*;
import org.springframework.web.multipart.MultipartFile;

public interface WxTPOfficialService {
    JumpQRCodeAddResponse addJumpQRCode(JumpQRCodeAddParameter parameter);

    JumpQRCodeDeleteResponse deleteJumpQRCode(String appId, String prefix);

    JumpQRCodeGetResponse getJumpQRCode(JumpQRCodeGetParameter parameter);

    JumpQRCodePublishResponse publishJumpQRCode(String appId, String prefix);

    LinkForShowGetResponse getLinkForShow(String appId, Integer page, Integer num);

    LinkMiniProgramGetResponse getLinkMiniProgram(String appId);

    MiniProgramLinkResponse linkMiniProgram(MiniProgramLinkParameter parameter);

    MiniProgramUnlinkResponse unlinkMiniProgram(String appId);

    ShowItemGetResponse getShowItem(String appId);

    ShowItemSetResponse setShowItem(String appId, Integer wxaSubscribeBizFlag);

    TempAssetAddResponse addTempAsset(String appId, MultipartFile file, String type);

    OfficialAccountAuthLinkGenerateResponse generateOfficialAccountAuthLink(
            OfficialAccountAuthLinkGenerateRequest request);
}
