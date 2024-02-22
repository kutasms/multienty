package com.chia.multienty.wechat.thirdparty.sdk.response.code;

import com.chia.multienty.wechat.thirdparty.sdk.response.BaseResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * 查询审核单状态响应数据
 */
@Data
public class AuditStatusGetResponse extends BaseResponse {
    /**
     * 审核状态
     */
    private Integer status;
    /**
     * 当 status = 1 时，返回的拒绝原因; status = 4 时，返回的延后原因
     */
    private String reason;
    /**
     * 当 status = 1 时，会返回审核失败的小程序截图示例。用竖线分隔的 media_id 的列表，可通过获取永久素材接口拉取截图内容
     */
    @JsonProperty(value = "screenshot")
    private String screenShot;
}
