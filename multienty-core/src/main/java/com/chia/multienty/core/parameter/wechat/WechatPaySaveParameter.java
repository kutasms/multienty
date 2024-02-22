package com.chia.multienty.core.parameter.wechat;

import lombok.Data;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.chia.multienty.core.annotation.LogMetaId;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
/**
 * <p>
 * 微信支付保存请求
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-01-21
 */

@Data
@ApiModel(value = "WechatPaySaveParameter",description = "微信支付保存请求")
public class WechatPaySaveParameter {

    /**
     * 微信支付编号
     */
    @ApiModelProperty(value = "微信支付编号")
    @LogMetaId
    @JsonIgnore
    @JsonProperty(required = false)
    private Long wxPayId;
    /**
     * 租户编号
     */
    @ApiModelProperty(value = "租户编号")
    private Long tenantId;
    /**
     * 商户号
     */
    @ApiModelProperty(value = "商户号")
    private String mchId;
    /**
     * 证书路径
     */
    @ApiModelProperty(value = "证书路径")
    private String certPath;
    /**
     * 商户私钥路径
     */
    @ApiModelProperty(value = "商户私钥路径")
    private String privateKeyPath;
    /**
     * 商户证书序列号
     */
    @ApiModelProperty(value = "商户证书序列号")
    private String serialNumber;
    /**
     * v3版本key
     */
    @ApiModelProperty(value = "v3版本key")
    private String apiV3Key;
    /**
     * v2版本key
     */
    @ApiModelProperty(value = "v2版本key")
    private String apiV2Key;
    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;
}
