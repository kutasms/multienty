package com.chia.multienty.core.parameter.base;

import lombok.Data;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
/**
 * <p>
 * 城市管理信息表详情获取请求
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-01-06
 */

@Data
@ApiModel(value = "ChineseCityDetailGetParameter",description = "城市管理信息表详情获取请求")
public class ChineseCityDetailGetParameter {
    /**
     * 城市id
     */
     @ApiModelProperty(value = "城市id")
     private Long cityId;
}
