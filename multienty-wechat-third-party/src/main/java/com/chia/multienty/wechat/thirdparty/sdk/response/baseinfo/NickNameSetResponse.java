package com.chia.multienty.wechat.thirdparty.sdk.response.baseinfo;

import com.chia.multienty.wechat.thirdparty.sdk.response.BaseResponse;
import lombok.Data;

/**
 * 设置小程序名称响应数据
 */
@Data
public class NickNameSetResponse extends BaseResponse {
    /**
     * 材料说明
     */
    private String wording;
    /**
     * 审核单 id，通过用于查询改名审核状态
     */
    private Long auditId;
}
