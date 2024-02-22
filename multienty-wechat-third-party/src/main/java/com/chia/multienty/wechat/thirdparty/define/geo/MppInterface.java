package com.chia.multienty.wechat.thirdparty.define.geo;

import lombok.Data;

/**
 * 地理位置相关隐私接口
 */
@Data
public class MppInterface {
    /**
     * 	api 英文名
     */
    private String apiName;
    /**
     * api 中文名
     */
    private String apiChName;
    /**
     * 	api描述
     */
    private String apiDesc;
    /**
     * 申请时间 ，该字段发起申请后才会有
     */
    private Long applyTime;
    /**
     * 接口状态，该字段发起申请后才会有
     */
    private String status;
    /**
     * 申请单号，该字段发起申请后才会有
     */
    private Long auditId;
    /**
     * 申请被驳回原因或者无权限，该字段申请驳回时才会有
     */
    private String failReason;
    /**
     * api文档链接
     */
    private String apiLink;
    /**
     * 分组名
     */
    private String groupName;
}
