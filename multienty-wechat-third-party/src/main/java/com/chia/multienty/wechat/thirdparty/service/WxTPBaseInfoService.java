package com.chia.multienty.wechat.thirdparty.service;


import com.chia.multienty.wechat.thirdparty.define.oa.WxMedia;
import com.chia.multienty.wechat.thirdparty.sdk.response.baseinfo.*;
import com.chia.multienty.wechat.thirdparty.sdk.response.baseinfo.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

public interface WxTPBaseInfoService {

    AccountBasicInfoGetResponse getAccountBasicInfo(String appId);

    BindOpenAccountGetResponse getBindOpenAccount(String appId);

    HeadImageSetResponse setHeadImage(String appId, MultipartFile file);

    LoginResponse login(String appId, String jsCode);

    NickNameCheckResponse checkNickName(String appId, String nickName);

    NickNameSetResponse setNickName(String appId, String nickName, Map<String, WxMedia> mediaMap);

    NickNameStatusGetResponse getNickNameStatus(String appId, Long auditId);

    SearchStatusGetResponse getSearchStatus(String appId);

    SearchStatusSetResponse setSearchStatus(String appId, Integer status);

    SignatureSetResponse setSignature(String appId, String signature);
}
