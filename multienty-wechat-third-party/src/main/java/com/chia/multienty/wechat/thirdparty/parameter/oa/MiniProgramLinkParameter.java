package com.chia.multienty.wechat.thirdparty.parameter.oa;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class MiniProgramLinkParameter {
    /**
     * 小程序 appid
     */
    private String appId;
    /**
     * 是否发送模板消息通知公众号粉丝。1表示是，0表示否
     */
    private Integer notifyUsers;
    /**
     * 是否展示公众号主页中。1表示是，0表示否
     */
    private Integer showProfile;
}
