package com.chia.multienty.wechat.thirdparty.sdk.request.code;

import com.chia.multienty.wechat.thirdparty.annotation.WxApi;
import com.chia.multienty.wechat.thirdparty.define.MerchantApis;
import com.chia.multienty.wechat.thirdparty.sdk.request.AuthorizerBaseRequest;
import com.chia.multienty.wechat.thirdparty.sdk.response.code.CodeReleaseRevertResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * 小程序版本回退
 */
@Data
@WxApi(url = MerchantApis.Code.REVERT_CODE_RELEASE, method = WxApi.Method.GET, postForm = true)
public class CodeReleaseRevertRequest extends AuthorizerBaseRequest<CodeReleaseRevertResponse> {
    /**
     * 只能填get_history_version。表示获取可回退的小程序版本。该参数为 URL 参数，非 Body 参数。
     */
    @JsonProperty("action")
    private String action;
    /**
     * 默认是回滚到上一个版本；也可回滚到指定的小程序版本，可通过get_history_version获取app_version。该参数为 URL 参数，非 Body 参数
     */
    @JsonProperty("app_version")
    private String appVersion;
}
