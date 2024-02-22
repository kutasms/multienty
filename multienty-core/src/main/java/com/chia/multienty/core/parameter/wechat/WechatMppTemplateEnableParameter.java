package com.chia.multienty.core.parameter.wechat;

import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
/**
 * <p>
 * 微信小程序模版启用请求
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-02-01
 */

@Data
@ApiModel(value = "WechatMppTemplateEnableParameter",description = "微信小程序模版启用请求")
public class WechatMppTemplateEnableParameter {
    /**
     * 微信小程序模版编号
     */
     @ApiModelProperty(value = "微信小程序模版编号")
     private Long mppTemplateId;
}
