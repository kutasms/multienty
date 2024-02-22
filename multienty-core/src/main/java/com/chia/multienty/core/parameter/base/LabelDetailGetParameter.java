package com.chia.multienty.core.parameter.base;

import lombok.Data;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
/**
 * <p>
 * 详情获取请求
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-01-06
 */

@Data
@ApiModel(value = "LabelDetailGetParameter",description = "详情获取请求")
public class LabelDetailGetParameter {
    /**
     * 标签编号
     */
     @ApiModelProperty(value = "标签编号")
     private Long labelId;
}
