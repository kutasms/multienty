package com.chia.multienty.wechat.thirdparty.sdk.response.geo;

import com.chia.multienty.wechat.thirdparty.sdk.response.BaseResponse;
import com.chia.multienty.wechat.thirdparty.define.geo.MppInterface;
import lombok.Data;

import java.util.List;

/**
 * 获取地理位置接口列表响应数据
 */
@Data
public class PrivacyInterfaceGetResponse extends BaseResponse {
    /**
     * 地理位置相关隐私接口
     */
    private List<MppInterface> interfaceList;
}
