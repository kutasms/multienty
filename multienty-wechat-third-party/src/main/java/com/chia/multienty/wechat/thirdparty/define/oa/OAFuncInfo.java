package com.chia.multienty.wechat.thirdparty.define.oa;

import lombok.Data;

/**
 * 微信认证及支付信息
 */
@Data
public class OAFuncInfo {
    /**
     * "微信认证"或者"微信支付"的id
     */
    private Long id;
    /**
     * 开通状态。0 表示未开通，1 表示开通
     */
    private Integer status;
    /**
     * "微信认证"或者"微信支付"
     */
    private String name;
}
