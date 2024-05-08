package com.chia.multienty.wechat.thirdparty.service;

import com.chia.multienty.wechat.thirdparty.sdk.response.geo.PrivacyInterfaceApplyResponse;
import com.chia.multienty.wechat.thirdparty.sdk.response.geo.PrivacyInterfaceGetResponse;
import com.chia.multienty.wechat.thirdparty.parameter.geo.PrivacyInterfaceApplyParameter;

public interface WxTPGeoService {

    PrivacyInterfaceApplyResponse applyPrivacyInterface(PrivacyInterfaceApplyParameter parameter);

    PrivacyInterfaceGetResponse getPrivacyInterface(String appId);
}
