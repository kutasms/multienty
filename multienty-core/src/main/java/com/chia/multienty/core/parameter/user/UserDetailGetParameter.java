package com.chia.multienty.core.parameter.user;

import lombok.Data;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
/**
 * <p>
 * 管理账户信息详情获取请求
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-01-06
 */

@Data
@ApiModel(value = "UserDetailGetParameter",description = "管理账户信息详情获取请求")
public class UserDetailGetParameter {
    /**
     * 账户id
     */
     @ApiModelProperty(value = "账户id")
     private Long userId;
}
