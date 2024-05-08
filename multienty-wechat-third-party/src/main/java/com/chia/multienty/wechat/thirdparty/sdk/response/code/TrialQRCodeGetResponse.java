package com.chia.multienty.wechat.thirdparty.sdk.response.code;

import com.chia.multienty.wechat.thirdparty.sdk.response.StreamingResponse;
import lombok.Data;

/**
 * 获取体验版二维码响应数据
 */
@Data
public class TrialQRCodeGetResponse extends StreamingResponse {
    private String qrcode;
}
