package com.chia.multienty.core.parameter.wechat;

import lombok.Data;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
/**
 * <p>
 * 微信支付详情获取请求
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-01-21
 */

@Data
@ApiModel(value = "WechatPayDetailGetParameter",description = "微信支付详情获取请求")
public class WechatPayDetailGetParameter {
    /**
     * 微信支付编号
     */
     @ApiModelProperty(value = "微信支付编号")
     private Long wxPayId;
}
