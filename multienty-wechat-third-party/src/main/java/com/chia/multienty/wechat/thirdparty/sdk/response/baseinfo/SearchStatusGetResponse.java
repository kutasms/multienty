package com.chia.multienty.wechat.thirdparty.sdk.response.baseinfo;

import com.chia.multienty.wechat.thirdparty.sdk.response.BaseResponse;
import lombok.Data;

/**
 * 获取搜索状态响应数据
 */
@Data
public class SearchStatusGetResponse extends BaseResponse {
    /**
     * 1 表示不可搜索，0 表示可搜索
     */
    private Integer status;
}
