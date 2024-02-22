package com.chia.multienty.wechat.thirdparty.sdk.response.baseinfo;

import com.chia.multienty.wechat.thirdparty.define.baseinfo.*;
import com.chia.multienty.wechat.thirdparty.sdk.response.BaseResponse;
import com.chia.multienty.wechat.thirdparty.define.baseinfo.*;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * 获取基本信息
 */
@Data
public class AccountBasicInfoGetResponse extends BaseResponse {
    /**
     * 帐号 appid
     */
    private String appid;
    /**
     * 帐号类型（1：订阅号，2：服务号，3：小程序）
     */
    private WxAccountType accountType;
    /**
     * 主体类型
     */
    private PrincipalType principalType;
    /**
     * 主体名称
     */
    private String principalName;
    /**
     * 实名验证状态
     */
    @JsonProperty(value = "realname_status")
    private RealNameStatus realNameStatus;
    /**
     * 微信认证信息
     */
    private WxVerifyInfo wxVerifyInfo;
    /**
     * 功能介绍信息
     */
    private MppSignatureInfo signatureInfo;
    /**
     * 头像信息
     */
    private MppHeadImageInfo headImageInfo;
    /**
     * 小程序名称
     */
    private String nickName;
    /**
     * 注册国家
     */
    private Integer registeredCountry;
    /**
     * 名称信息
     */
    @JsonProperty(value = "nickname_info")
    private MppNickNameInfo nickNameInfo;
    /**
     * 非个人主体时返回的是企业或者政府或其他组织的代号
     */
    private String credential;
    /**
     * 认证类型；如果未完成微信认证则返回0；不同枚举值对应的类型说明看下方
     */
    private WxCustomerType customerType;
}
