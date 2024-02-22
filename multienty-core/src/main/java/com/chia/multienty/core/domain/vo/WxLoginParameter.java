package com.chia.multienty.core.domain.vo;

import lombok.Data;

/**
 * 微信登录参数
 */
@Data
public class WxLoginParameter {
    private String code;
    private String iv;
    private String encryptedData;
    private String promoterId;
}
