package com.chia.multienty.wechat.thirdparty.define.geo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * 地理位置相关隐私接口
 */
@Data
public class MppInterface {
    /**
     * 	api 英文名
     */
    @JsonProperty("api_name")
    private String apiName;
    /**
     * api 中文名
     */
    @JsonProperty("api_ch_name")
    private String apiChName;
    /**
     * 	api描述
     */
    @JsonProperty("api_desc")
    private String apiDesc;
    /**
     * 申请时间 ，该字段发起申请后才会有
     */
    @JsonProperty("apply_time")
    private Long applyTime;
    /**
     * 接口状态，该字段发起申请后才会有
     */
    @JsonProperty("status")
    private String status;
    /**
     * 申请单号，该字段发起申请后才会有
     */
    @JsonProperty("audit_id")
    private Long auditId;
    /**
     * 申请被驳回原因或者无权限，该字段申请驳回时才会有
     */
    @JsonProperty("fail_reason")
    private String failReason;
    /**
     * api文档链接
     */
    @JsonProperty("api_link")
    private String apiLink;
    /**
     * 分组名
     */
    @JsonProperty("group_name")
    private String groupName;
}
