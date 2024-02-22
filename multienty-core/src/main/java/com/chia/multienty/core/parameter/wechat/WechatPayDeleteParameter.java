package com.chia.multienty.core.parameter.wechat;

import lombok.Data;
import com.chia.multienty.core.annotation.LogMetaId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
/**
 * <p>
 * 微信支付删除请求
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-02-01
 */

@Data
@ApiModel(value = "WechatPayDeleteParameter",description = "微信支付删除请求")
public class WechatPayDeleteParameter {

    /**
     * 微信支付编号
     */
     @ApiModelProperty(value = "微信支付编号")
     @LogMetaId
     private Long wxPayId;
}
