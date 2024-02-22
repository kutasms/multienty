package com.chia.multienty.core.parameter.wechat;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.experimental.Accessors;

/**
 * <p>
 * 微信应用详情获取请求
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-01-26
 */

@Data
@Accessors(chain = true)
@ApiModel(value = "WechatAppDetailGetParameter",description = "微信应用详情获取请求")
public class WechatAppDetailGetParameter implements Serializable {
     private Long programId;

     private Boolean containsTemplates;
     private Boolean containsPay;
}
