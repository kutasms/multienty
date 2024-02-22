package com.chia.multienty.core.parameter.wechat;

import lombok.Data;
import com.chia.multienty.core.annotation.LogMetaId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
/**
 * <p>
 * 权限集删除请求
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-02-01
 */

@Data
@ApiModel(value = "WechatAppFuncScopeDeleteParameter",description = "权限集删除请求")
public class WechatAppFuncScopeDeleteParameter {

    /**
     * 权限集关联编号
     */
     @ApiModelProperty(value = "权限集关联编号")
     @LogMetaId
     private Long fsId;
}
