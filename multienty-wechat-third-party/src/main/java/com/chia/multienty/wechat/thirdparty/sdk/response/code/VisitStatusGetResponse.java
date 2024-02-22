package com.chia.multienty.wechat.thirdparty.sdk.response.code;

import com.chia.multienty.wechat.thirdparty.sdk.response.BaseResponse;
import lombok.Data;

/**
 * 查询小程序服务状态响应数据
 */
@Data
public class VisitStatusGetResponse extends BaseResponse {
    /**
     * 服务状态。0表示已暂停服务（包含主动暂停服务违规被暂停服务）。1表示未暂停服务
     */
    private Integer status;
}
