package com.chia.multienty.core.parameter.base;

import lombok.Data;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
/**
 * <p>
 * 数据字典信息详情获取请求
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-01-06
 */

@Data
@ApiModel(value = "DictDetailGetParameter",description = "数据字典信息详情获取请求")
public class DictDetailGetParameter {
    /**
     * 字典id
     */
     @ApiModelProperty(value = "字典id")
     private Long dictId;
}
