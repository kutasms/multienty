package com.chia.multienty.core.domain.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 上传文件类型枚举
 */
@Getter
@AllArgsConstructor
@ApiModel(value = "UploadFileType",description = "上传文件类型枚举")
public enum UploadFileType {
    UNKNOWN("未知", 0),
    QRCODE("二维码", 1),

    AVATAR("头像", 3),
    IDCARD_FRONT("身份证正面", 4),
    IDCARD_BACK("身份证反面", 5),
    BANK_CARD("银行卡", 6),
    BUSINESS_LICENSE("营业执照", 7),
    SERVICE_PROTOCOL("协议文件", 8),
    QRCODE_PROMOTION("推广二维码", 9),
    CASE_MAIN_IMAGE("案例主图", 10),
    CASE_SUB_IMAGE("案例子图", 11),
    PRODUCT_VIDEO("商品主视频", 12),
    SKU_IMAGE("SKU图片", 13),
    RICH_TXT("详情嵌套", 14),
    PRODUCT_IMAGE("商品图片", 15),
    CATEGORY_AVATAR("类目图片", 16),
    PRIVATE_KEY("密钥文件", 17),
    CERTIFICATE("加密证书", 18);

    /**
     * 描述
     */
    @ApiModelProperty("描述")
    private String describe;

    /**
     * 值
     */
    @ApiModelProperty("值")
    @JsonValue
    private Integer value;

    @JsonCreator
    public static UploadFileType parse(Integer value) {
        if (value == null) {
            return null;
        }
        for (UploadFileType type : values()) {
            if (type.getValue().equals(value)) {
                return type;
            }
        }
        return UploadFileType.UNKNOWN;
    }
}