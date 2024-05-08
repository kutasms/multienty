package com.chia.multienty.core.parameter.wechat;

import lombok.Data;
import com.chia.multienty.core.annotation.LogMetaId;
import javax.validation.constraints.NotNull;
import com.chia.multienty.core.pojo.KutaBaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.experimental.Accessors;
/**
 * <p>
 * 权限集删除请求
 * </p>
 *
 * @author Multienty Auto Generator
 * @since 2024-04-15
 */

@Data
@ApiModel(value = "WechatAppFuncScopeDeleteParameter",description = "权限集删除请求")
@Accessors(chain = true)
public class WechatAppFuncScopeDeleteParameter {

    /**
     * 权限集关联编号
     */
    @ApiModelProperty(value = "权限集关联编号")
    @LogMetaId
    private Long fsId;
    /**
     * 租户编号
     */
    @ApiModelProperty(value = "租户编号")
    @NotNull
    private Long tenantId;

}
