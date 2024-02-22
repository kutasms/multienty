package com.chia.multienty.wechat.thirdparty.define.oa;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * 	公众号信息
 */
@Data
public class BizInfo {
    /**
     * 公众号昵称
     */
    @JsonProperty(value = "nickname")
    private String nickName;
    /**
     * 公众号 appid
     */
    @JsonProperty(value = "appid")
    private String appId;
    /**
     * 公众号头像
     */
    @JsonProperty(value = "headimg")
    private String headImage;
}
