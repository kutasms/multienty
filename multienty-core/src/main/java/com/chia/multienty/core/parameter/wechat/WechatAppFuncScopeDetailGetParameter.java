package com.chia.multienty.core.parameter.wechat;

import lombok.Data;
import com.chia.multienty.core.pojo.KutaBaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotNull;
import lombok.experimental.Accessors;
/**
 * <p>
 * 权限集详情获取请求
 * </p>
 *
 * @author Multienty Auto Generator
 * @since 2024-04-15
 */

@Data
@Accessors(chain = true)
@ApiModel(value = "WechatAppFuncScopeDetailGetParameter",description = "权限集详情获取请求")
public class WechatAppFuncScopeDetailGetParameter {
    /**
     * 权限集关联编号
     */
     @ApiModelProperty(value = "权限集关联编号")
     private Long fsId;
    /**
     * 租户编号
     */
    @ApiModelProperty(value = "租户编号")
    @NotNull
    private Long tenantId;
}
