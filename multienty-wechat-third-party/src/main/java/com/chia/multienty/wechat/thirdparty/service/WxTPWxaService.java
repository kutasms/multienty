package com.chia.multienty.wechat.thirdparty.service;

import com.chia.multienty.wechat.thirdparty.define.wxa.WxAuthData;
import com.chia.multienty.wechat.thirdparty.sdk.response.wxa.WxAuthMaterialUploadResponse;
import com.chia.multienty.wechat.thirdparty.sdk.response.wxa.WxAuthProgressQueryResponse;
import com.chia.multienty.wechat.thirdparty.sdk.response.wxa.WxAuthResponse;
import com.chia.multienty.wechat.thirdparty.sdk.response.wxa.WxReAuthResponse;
import org.springframework.web.multipart.MultipartFile;

public interface WxTPWxaService {

    WxAuthMaterialUploadResponse uploadWxAuthMaterial(MultipartFile file, String appId);

    WxAuthProgressQueryResponse queryWxAuthProgress(String appId, String taskId);

    WxAuthResponse authMiniProgram(String appId, WxAuthData authData);

    WxReAuthResponse reAuthMiniProgram(String appId, WxAuthData authData);
}
