package com.chia.multienty.core.parameter.wechat;

import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
/**
 * <p>
 * 微信支付禁用请求
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-02-01
 */

@Data
@ApiModel(value = "WechatPayDisableParameter",description = "微信支付禁用请求")
public class WechatPayDisableParameter {
    /**
     * 微信支付编号
     */
     @ApiModelProperty(value = "微信支付编号")
     private Long wxPayId;
}
