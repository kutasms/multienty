package com.chia.multienty.wechat.thirdparty.sdk.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class BaseResponse {
    /**
     * 错误码
     */
    @JsonProperty(value = "errcode")
    private Integer errCode;
    /**
     * 错误信息
     */
    @JsonProperty(value = "errmsg")
    private String errMsg;

    /**
     * 是否成功
     * @return
     */
    @JsonIgnore
    public boolean isSucceed() {
        return errCode == null || errCode.equals("0");
    }
}
