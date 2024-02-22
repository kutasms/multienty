package com.chia.multienty.core.parameter.user;

import lombok.Data;
import com.chia.multienty.core.annotation.LogMetaId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
/**
 * <p>
 * 用户删除请求
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-02-01
 */

@Data
@ApiModel(value = "UserDeleteParameter",description = "用户删除请求")
public class UserDeleteParameter {

    /**
     * 用户编号
     */
     @ApiModelProperty(value = "用户编号")
     @LogMetaId
     private Long userId;
}
