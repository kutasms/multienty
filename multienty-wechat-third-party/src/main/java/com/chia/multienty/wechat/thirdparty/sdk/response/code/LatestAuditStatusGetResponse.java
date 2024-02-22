package com.chia.multienty.wechat.thirdparty.sdk.response.code;

import com.chia.multienty.wechat.thirdparty.sdk.response.BaseResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * 查询最新一次审核单状态响应数据
 */
@Data
public class LatestAuditStatusGetResponse extends BaseResponse {
    /**
     * 最新的审核id
     */
    @JsonProperty(value = "auditid")
    private Long auditId;
    /**
     * 审核状态
     */
    private Integer status;
    /**
     * 当审核被拒绝时，返回的拒绝原因
     */
    private String reason;
    /**
     * 当审核被拒绝时，会返回审核失败的小程序截图示例。
     * 用 竖线I 分隔的 media_id 的列表，可通过获取永久素材接口拉取截图内容
     */
    private String screenShot;
    /**
     * 审核版本
     */
    private String userVersion;
    /**
     * 版本描述
     */
    private String userDesc;
    /**
     * 时间戳，提交审核的时间
     */
    private Long submitAuditTime;
}
