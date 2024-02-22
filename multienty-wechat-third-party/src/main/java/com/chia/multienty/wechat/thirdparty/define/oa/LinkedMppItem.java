package com.chia.multienty.wechat.thirdparty.define.oa;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;
/**
 * 关联的小程序信息
 */
@Data
public class LinkedMppItem {
    /**
     * 小程序名称
     */
    @JsonProperty(value = "nickname")
    private String nickName;
    /**
     * 头像 url
     */
    @JsonProperty("headimg_url")
    private String headImageUrl;
    /**
     * 关联状态。1表示已关联；2表示等待小程序管理员确认中；3表示小程序管理员拒绝关联；12表示等待公众号管理员确认中
     */
    private Integer status;
    /**
     * 小程序 appid
     */
    @JsonProperty(value = "appid")
    private String appId;
    /**
     * 微信认证及支付信息
     */
    private List<OAFuncInfo> funcInfos;
    /**
     * 是否在公众号管理页展示中。1表示是，0表示否。
     */
    private Integer selected;
    /**
     * 小程序原始id
     */
    private String username;
    /**
     * 小程序邮箱
     */
    private String email;
    /**
     * 是否展示在附近的小程序中。1表示是，0表示否
     */
    private Integer nearbyDisplayStatus;
    /**
     * 是否已经发布。1表示是，0表示否
     */
    private Integer released;
}
