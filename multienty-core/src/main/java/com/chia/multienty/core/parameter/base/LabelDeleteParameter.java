package com.chia.multienty.core.parameter.base;

import lombok.Data;
import com.chia.multienty.core.annotation.LogMetaId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
/**
 * <p>
 * 标签删除请求
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-02-01
 */

@Data
@ApiModel(value = "LabelDeleteParameter",description = "标签删除请求")
public class LabelDeleteParameter {

    /**
     * 标签编号
     */
     @ApiModelProperty(value = "标签编号")
     @LogMetaId
     private Long labelId;
}
