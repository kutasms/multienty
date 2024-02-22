package com.chia.multienty.core.parameter.base;

import lombok.Data;
import com.chia.multienty.core.annotation.LogMetaId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
/**
 * <p>
 * Json配置删除请求
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-02-01
 */

@Data
@ApiModel(value = "JsonConfigDeleteParameter",description = "Json配置删除请求")
public class JsonConfigDeleteParameter {

    /**
     * 配置编号
     */
     @ApiModelProperty(value = "配置编号")
     @LogMetaId
     private Long configId;
}
