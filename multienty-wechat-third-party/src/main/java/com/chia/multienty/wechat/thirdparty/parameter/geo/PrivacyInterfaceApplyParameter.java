package com.chia.multienty.wechat.thirdparty.parameter.geo;

import lombok.Data;

import java.util.List;

@Data
public class PrivacyInterfaceApplyParameter {
    /**
     * 申请的 api 英文名，例如wx.choosePoi，严格区分大小写
     */
    private String apiName;
    /**
     * 申请说原因，不超过300个字符；需要以utf-8编码提交，否则会出现审核失败
     */
    private String content;
    /**
     * (辅助网页)例如，上传官网网页链接用于辅助审核
     */
    private List<String> urlList;
    /**
     * (辅助图片)填写图片的url ，最多10个
     */
    private List<String> picList;
    /**
     * (辅助视频)填写视频的链接 ，最多支持1个；视频格式只支持mp4格式
     */
    private List<String> videoList;
}
