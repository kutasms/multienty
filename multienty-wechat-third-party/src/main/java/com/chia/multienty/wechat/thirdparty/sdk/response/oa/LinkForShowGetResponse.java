package com.chia.multienty.wechat.thirdparty.sdk.response.oa;

import com.chia.multienty.wechat.thirdparty.sdk.response.BaseResponse;
import com.chia.multienty.wechat.thirdparty.define.oa.BizInfo;
import lombok.Data;

import java.util.List;

/**
 * 获取可设置公众号列表响应数据
 */
@Data
public class LinkForShowGetResponse extends BaseResponse {
    /**
     * 	总记录数
     */
    private Long totalNum;
    /**
     * 公众号信息列表
     */
    private List<BizInfo> bizInfoList;
}
