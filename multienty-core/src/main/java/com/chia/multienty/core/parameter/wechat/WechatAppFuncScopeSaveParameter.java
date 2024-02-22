package com.chia.multienty.core.parameter.wechat;

import lombok.Data;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.chia.multienty.core.annotation.LogMetaId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
/**
 * <p>
 * 权限集保存请求
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-01-26
 */

@Data
@ApiModel(value = "WechatAppFuncScopeSaveParameter",description = "权限集保存请求")
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
