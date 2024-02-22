package com.chia.multienty.core.parameter.base;

import lombok.Data;
import com.chia.multienty.core.annotation.LogMetaId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
/**
 * <p>
 * 中国城市删除请求
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-02-01
 */

@Data
@ApiModel(value = "ChineseCityDeleteParameter",description = "中国城市删除请求")
public class ChineseCityDeleteParameter {

    /**
     * 城市编号
     */
     @ApiModelProperty(value = "城市编号")
     @LogMetaId
     private Long cityId;
}
