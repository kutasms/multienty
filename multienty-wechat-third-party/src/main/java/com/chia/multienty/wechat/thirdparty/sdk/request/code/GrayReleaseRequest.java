package com.chia.multienty.wechat.thirdparty.sdk.request.code;

import com.chia.multienty.wechat.thirdparty.annotation.WxApi;
import com.chia.multienty.wechat.thirdparty.define.MerchantApis;
import com.chia.multienty.wechat.thirdparty.sdk.request.AuthorizerBaseRequest;
import com.chia.multienty.wechat.thirdparty.sdk.response.code.GrayReleaseResponse;
import lombok.Data;

/**
 * 分阶段发布
 */
@Data
@WxApi(url = MerchantApis.Code.GRAY_RELEASE)
public class GrayReleaseRequest extends AuthorizerBaseRequest<GrayReleaseResponse> {
    /**
     * 灰度的百分比 0~ 100 的整。如果gray_percentage=0，support_experiencer_first与support_debuger_first二选一必填
     */
    private Integer grayPercentage;
    /**
     * true表示支持按体验成员灰度，默认是false
     */
    private Boolean supportDebugerFirst;
    /**
     * true表示支持按项目成员灰度，默认是false
     */
    private Boolean supportExperiencerFirst;
}
