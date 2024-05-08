package com.chia.multienty.wechat.thirdparty.sdk.request.baseinfo;

import com.chia.multienty.wechat.thirdparty.define.MerchantApis;
import com.chia.multienty.wechat.thirdparty.annotation.WxApi;
import com.chia.multienty.wechat.thirdparty.sdk.request.AuthorizerBaseRequest;
import com.chia.multienty.wechat.thirdparty.sdk.response.baseinfo.NickNameSetResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * 设置小程序名称
 */
@Data
@WxApi(url = MerchantApis.BaseInfo.SET_NICK_NAME)
public class NickNameSetRequest extends AuthorizerBaseRequest<NickNameSetResponse> {
    /**
     * 昵称，不支持包含“小程序”关键字的昵称
     */
    @JsonProperty("nick_name")
    private String nickName;
    /**
     * 身份证照片 mediaid，个人号必填
     */
    @JsonProperty(value = "id_card")
    private String idcard;
    /**
     * 组织机构代码证或营业执照 mediaid，组织号必填
     */
    @JsonProperty("license")
    private String license;
    /**
     * 其他证明材料 mediaid
     */
    @JsonProperty(value = "naming_other_stuff_1")
    private String namingOtherStuff1;
    /**
     * 其他证明材料 mediaid
     */
    @JsonProperty(value = "naming_other_stuff_2")
    private String namingOtherStuff2;
    /**
     * 其他证明材料 mediaid
     */
    @JsonProperty(value = "naming_other_stuff_3")
    private String namingOtherStuff3;
    /**
     * 其他证明材料 mediaid
     */
    @JsonProperty(value = "naming_other_stuff_4")
    private String namingOtherStuff4;
    /**
     * 其他证明材料 mediaid
     */
    @JsonProperty(value = "naming_other_stuff_5")
    private String namingOtherStuff5;
}
