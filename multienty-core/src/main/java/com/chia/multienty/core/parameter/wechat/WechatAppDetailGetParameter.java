package com.chia.multienty.core.parameter.wechat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * <p>
 * 微信应用详情获取请求
 * </p>
 *
 * @author Multienty Auto Generator
 * @since 2024-04-15
 */

@Data
@Accessors(chain = true)
@ApiModel(value = "WechatAppDetailGetParameter",description = "微信应用详情获取请求")
public class WechatAppDetailGetParameter implements Serializable {
     private Long programId;
    /**
     * 租户编号
     */
    @ApiModelProperty(value = "租户编号")
    @NotNull
    private Long tenantId;
    /**
     * 是否包含模板
     */
    @ApiModelProperty(value = "是否包含模板")
    private Boolean containsTemplates = false;
    /**
     * 是否包含支付信息
     */
    @ApiModelProperty(value = "是否包含支付信息")
    private Boolean containsPay = true;
}
