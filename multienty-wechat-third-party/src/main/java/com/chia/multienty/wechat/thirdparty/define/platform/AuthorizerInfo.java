package com.chia.multienty.wechat.thirdparty.define.platform;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class AuthorizerInfo {
    /**
     * 昵称
     */
    private String nickName;
    /**
     * 头像
     */
    private String headImg;
    /**
     * 公众号/小程序类型
     */
    private ServiceTypeInfo serviceTypeInfo;
    /**
     * 公众号/小程序认证类型
     */
    private VerifyTypeInfo verifyTypeInfo;
    /**
     * 原始ID
     */
    private String userName;
    /**
     * 公众号所设置的微信号，可能为空
     */
    private String alias;
    /**
     * 二维码图片的 URL
     */
    private String qrcodeUrl;
    /**
     * 用以了解功能的开通状况（0代表未开通，1代表已开通）
     */
    private BusinessInfo businessInfo;

    /**
     * 主体名称
     */
    private String principalName;
    /**
     * 小程序帐号介绍
     */
    private String signature;

    /**
     * 小程序配置信息，可根据这个字段判断是否为小程序类型授权
     */
    @JsonProperty(value = "MiniProgramInfo")
    private MppInfo mppInfo;

    /**
     * 小程序注册方式
     */
    private Integer registerType;

    /**
     * 帐号状态，该字段小程序也返回
     */
    private Integer accountStatus;

    /**
     * 基础配置信息
     */
    private MppBasicConfig basicConfig;
}
