package com.chia.multienty.wechat.thirdparty.define.cateory;

import lombok.Data;

/**
 * 小程序资质证书
 */
@Data
public class MppCertificate {
    /**
     * 资质名称
     */
    private String key;
    /**
     * 资质图片，要填media_id(可通过新增临时素材接口获取)
     */
    private String value;
    private Long expireTime;
    private Integer isPermanent;
}
