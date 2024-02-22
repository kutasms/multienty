package com.chia.multienty.wechat.thirdparty.sdk.response;

import lombok.Data;

@Data
public class StreamingResponse extends BaseResponse{
    private byte[] stream;
}
