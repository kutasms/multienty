package com.chia.multienty.wechat.thirdparty.sdk.request.oa;

import com.chia.multienty.wechat.thirdparty.annotation.WxApi;
import com.chia.multienty.wechat.thirdparty.define.MerchantApis;
import com.chia.multienty.wechat.thirdparty.sdk.response.oa.JumpQRCodeGetResponse;
import com.chia.multienty.wechat.thirdparty.sdk.request.AuthorizerBaseRequest;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * 获取已设置的二维码规则
 */
@Data
@WxApi(url = MerchantApis.OfficialAccount.GET_JUMP_QRCODE)
public class JumpQRCodeGetRequest extends AuthorizerBaseRequest<JumpQRCodeGetResponse> {
    /**
     * 小程序 appid
     */
    @JsonProperty(value = "appid")
    private String appId;
    /**
     * 默认值为0。 0：查询最近新增 10000 条（数量大建议用1或者2）；
     * 1：prefix查询；
     * 2：分页查询，按新增顺序返回。
     * 获取“扫服务号二维码打开小程序”已设置的二维码规则才需要传这个参数
     */
    @JsonProperty(value = "get_type")
    private Integer type;
    /**
     * prefix查询，get_type=1 必传，最多传 200 个前缀。
     * 获取“扫服务号二维码打开小程序”已设置的二维码规则才需要传这个参数
     */
    private List<String> prefixList;
    /**
     * 页码，get_type=2 必传，从 1 开始。
     * 获取“扫服务号二维码打开小程序”已设置的二维码规则才需要传这个参数
     */
    private Integer pageNum;
    /**
     * 每页数量，get_type=2 必传，最大为 200。
     * 获取“扫服务号二维码打开小程序”已设置的二维码规则才需要传这个参数
     */
    private Integer pageSize;
}
