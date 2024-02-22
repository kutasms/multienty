package com.chia.multienty.core.parameter.base;

import lombok.Data;
import com.chia.multienty.core.annotation.LogMetaId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
/**
 * <p>
 * 数据字典删除请求
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-02-01
 */

@Data
@ApiModel(value = "DictDeleteParameter",description = "数据字典删除请求")
public class DictDeleteParameter {

    /**
     * 字典编号
     */
     @ApiModelProperty(value = "字典编号")
     @LogMetaId
     private Long dictId;
}
