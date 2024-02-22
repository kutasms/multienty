package com.chia.multienty.wechat.thirdparty.sdk.response.code;

import com.chia.multienty.wechat.thirdparty.sdk.response.BaseResponse;
import com.chia.multienty.wechat.thirdparty.define.code.UVInfo;
import lombok.Data;

/**
 * 查询各版本用户占比响应数据
 */
@Data
public class SupportVersionGetResponse extends BaseResponse {
    /**
     * 	当前版本
     */
    private String nowVersion;
    /**
     * 版本的用户占比列表
     */
    private UVInfo uvInfo;
}
