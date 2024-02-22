package com.chia.multienty.wechat.thirdparty.sdk.response.wxa;

import com.chia.multienty.wechat.thirdparty.sdk.response.BaseResponse;
import com.chia.multienty.wechat.thirdparty.define.wxa.WxAuthStatus;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * 小程序认证进度查询响应数据
 */
@Data
public class WxAuthProgressQueryResponse extends BaseResponse {
    private WxAuthStatus taskStatus;
    /**
     * 小程序管理员授权链接
     */
    private String authUrl;
    /**
     * 审核单状态，创建认证审核审核单成功后该值有效
     */
    private Integer applyStatus;
    /**
     * 小程序后台展示的认证订单号
     */
    @JsonProperty(value = "orderid")
    private Long orderId;
    /**
     * 当审核单被打回重填(apply_status=3)时有效
     */
    private String refillReason;
    /**
     * 审核最终失败的原因(apply_status=5)时有效
     */
    private String failReason;
}
