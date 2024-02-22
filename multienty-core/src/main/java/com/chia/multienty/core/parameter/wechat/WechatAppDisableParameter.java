package com.chia.multienty.core.parameter.wechat;

import lombok.Data;
import io.swagger.annotations.ApiModel;

/**
 * <p>
 * 微信应用禁用请求
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-02-01
 */

@Data
@ApiModel(value = "WechatAppDisableParameter",description = "微信应用禁用请求")
public class WechatAppDisableParameter {
     private Long programId;
}
