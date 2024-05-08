package com.chia.multienty.core.parameter.wechat;

import lombok.Data;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.chia.multienty.core.annotation.LogMetaId;
import com.chia.multienty.core.pojo.KutaBaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotNull;
import lombok.experimental.Accessors;
/**
 * <p>
 * 权限集保存请求
 * </p>
 *
 * @author Multienty Auto Generator
 * @since 2024-04-15
 */

@Data
@ApiModel(value = "WechatAppFuncScopeSaveParameter",description = "权限集保存请求")
@Accessors(chain = true)
public class WechatAppFuncScopeSaveParameter {

    /**
     * 权限集关联编号
     */
    @ApiModelProperty(value = "权限集关联编号")
    @LogMetaId
    @JsonIgnore
    @JsonProperty(required = false)
    private Long fsId;
    /**
     * app编号
     */
    @ApiModelProperty(value = "app编号")
    private Long programId;
    /**
     * 租户编号
     */
    @ApiModelProperty(value = "租户编号")
    @NotNull
    private Long tenantId;
    /**
     * 权限集id
     */
    @ApiModelProperty(value = "权限集id")
    private Integer funcScopeId;
    /**
     * 权限集类型
     */
    @ApiModelProperty(value = "权限集类型")
    private Integer type;
}
