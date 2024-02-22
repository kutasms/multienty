package com.chia.multienty.core.parameter.wechat;

import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
/**
 * <p>
 * 权限集详情获取请求
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-01-26
 */

@Data
@ApiModel(value = "WechatAppFuncScopeDetailGetParameter",description = "权限集详情获取请求")
public class WechatAppFuncScopeDetailGetParameter {
    /**
     * 权限集关联编号
     */
     @ApiModelProperty(value = "权限集关联编号")
     private Long fsId;
}
