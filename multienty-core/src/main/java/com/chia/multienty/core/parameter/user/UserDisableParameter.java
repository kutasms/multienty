package com.chia.multienty.core.parameter.user;

import com.chia.multienty.core.annotation.LogMetaId;
import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
/**
 * <p>
 * 用户禁用请求
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-02-01
 */

@Data
@ApiModel(value = "UserDisableParameter",description = "用户禁用请求")
public class UserDisableParameter {
    /**
     * 用户编号
     */
     @ApiModelProperty(value = "用户编号")
     @LogMetaId
     private Long userId;
}
