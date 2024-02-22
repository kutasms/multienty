package com.chia.multienty.core.parameter.user;

import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
/**
 * <p>
 * 用户启用请求
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-02-01
 */

@Data
@ApiModel(value = "UserEnableParameter",description = "用户启用请求")
public class UserEnableParameter {
    /**
     * 用户编号
     */
     @ApiModelProperty(value = "用户编号")
     private Long userId;
}
