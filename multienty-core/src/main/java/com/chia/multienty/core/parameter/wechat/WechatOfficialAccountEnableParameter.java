package com.chia.multienty.core.parameter.wechat;

import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
/**
 * <p>
 * 微信公众号账户启用请求
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-02-01
 */

@Data
@ApiModel(value = "WechatOfficialAccountEnableParameter",description = "微信公众号账户启用请求")
public class WechatOfficialAccountEnableParameter {
    /**
     * 微信公众号账户编号
     */
     @ApiModelProperty(value = "微信公众号账户编号")
     private Long woaId;
}
