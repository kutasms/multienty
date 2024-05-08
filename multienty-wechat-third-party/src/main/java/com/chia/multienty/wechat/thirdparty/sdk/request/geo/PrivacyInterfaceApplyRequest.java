package com.chia.multienty.wechat.thirdparty.sdk.request.geo;

import com.chia.multienty.wechat.thirdparty.define.MerchantApis;
import com.chia.multienty.wechat.thirdparty.annotation.WxApi;
import com.chia.multienty.wechat.thirdparty.sdk.request.AuthorizerBaseRequest;
import com.chia.multienty.wechat.thirdparty.sdk.response.geo.PrivacyInterfaceApplyResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * 申请地理位置接口
 */
@Data
@WxApi(url = MerchantApis.GEO.APPLY_PRIVACY_INTERFACE)
public class PrivacyInterfaceApplyRequest extends AuthorizerBaseRequest<PrivacyInterfaceApplyResponse> {
    /**
     * 申请的 api 英文名，例如wx.choosePoi，严格区分大小写
     */
    @JsonProperty("api_name")
    private String apiName;
    /**
     * 申请说原因，不超过300个字符；需要以utf-8编码提交，否则会出现审核失败
     */
    @JsonProperty("content")
    private String content;
    /**
     * (辅助网页)例如，上传官网网页链接用于辅助审核
     */
    @JsonProperty("url_list")
    private List<String> urlList;
    /**
     * (辅助图片)填写图片的url ，最多10个
     */
    @JsonProperty("pic_list")
    private List<String> picList;
    /**
     * (辅助视频)填写视频的链接 ，最多支持1个；视频格式只支持mp4格式
     */
    @JsonProperty("video_list")
    private List<String> videoList;
}
