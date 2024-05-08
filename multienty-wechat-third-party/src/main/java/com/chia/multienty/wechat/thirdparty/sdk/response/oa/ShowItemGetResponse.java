package com.chia.multienty.wechat.thirdparty.sdk.response.oa;

import com.chia.multienty.wechat.thirdparty.sdk.response.BaseResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * 获取已设置公众号信息响应数据
 */
@Data
public class ShowItemGetResponse extends BaseResponse {
    /**
     * 是否可以设置 1 可以，0，不可以
     */
    @JsonProperty("can_open")
    private Integer canOpen;
    /**
     * 是否已经设置，1 已设置，0，未设置
     */
    @JsonProperty("is_open")
    private Integer isOpen;
    /**
     * 展示的公众号 appid
     */
    @JsonProperty(value = "appid")
    private String appId;
    /**
     * 展示的公众号 nickname
     */
    @JsonProperty(value = "nickname")
    private String nickName;
    /**
     * 展示的公众号头像
     */
    @JsonProperty(value = "headimg")
    private String headImage;
}
