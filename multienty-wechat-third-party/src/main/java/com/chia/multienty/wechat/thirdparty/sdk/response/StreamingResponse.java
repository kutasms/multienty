package com.chia.multienty.wechat.thirdparty.sdk.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class StreamingResponse extends BaseResponse{
    @JsonIgnore
    private byte[] stream;
}
