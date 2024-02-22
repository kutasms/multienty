package com.chia.multienty.wechat.thirdparty.sdk.response.oa;

import com.chia.multienty.wechat.thirdparty.sdk.response.BaseResponse;
import com.chia.multienty.wechat.thirdparty.define.oa.QRCodeRule;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * 获取已设置的二维码规则响应数据
 */
@Data
public class JumpQRCodeGetResponse extends BaseResponse {
    /**
     * 二维码规则详情列表
     */
    private List<QRCodeRule> ruleList;

    /**
     * 是否已经打开二维码跳转链接设置
     */
    @JsonProperty(value = "qrcodejump_open")
    private Integer qrcodeJumpOpen;
    /**
     * 二维码规则数量
     */
    private Integer listSize;
    /**
     * 本月还可发布的次数
     */
    @JsonProperty(value = "qrcodejump_pub_quota")
    private Integer qrcodeJumpPubQuota;
    /**
     * 二维码规则总数据量，用于分页查询
     */
    private Integer totalCount;
}
