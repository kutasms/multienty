package com.chia.multienty.wechat.thirdparty.exception;

import lombok.Data;

@Data
public class WxThirdPartyException extends RuntimeException {
    private Integer errCode;
    public WxThirdPartyException(Integer errCode, String errMsg) {
        super(errMsg);
        this.errCode = errCode;
    }
}
