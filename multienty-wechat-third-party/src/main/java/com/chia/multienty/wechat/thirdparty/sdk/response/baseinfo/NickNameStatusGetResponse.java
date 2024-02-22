package com.chia.multienty.wechat.thirdparty.sdk.response.baseinfo;

import com.chia.multienty.wechat.thirdparty.sdk.response.BaseResponse;
import com.chia.multienty.wechat.thirdparty.define.baseinfo.AuditStatus;
import lombok.Data;

/**
 * 查询小程序名称审核状态响应数据
 */
@Data
public class NickNameStatusGetResponse extends BaseResponse {
    /**
     * 审核昵称
     */
    private String nickName;
    /**
     * 审核状态，1：审核中，2：审核失败，3：审核成功
     */
    private AuditStatus auditStat;
    /**
     * 失败原因
     */
    private String failReason;
    /**
     * 	审核提交时间
     */
    private Long createTime;
    /**
     * 审核完成时间
     */
    private Long auditTime;
}
