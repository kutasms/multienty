package com.chia.multienty.core.parameter.plugin;

import com.chia.multienty.core.annotation.LogMetaId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
/**
 * <p>
 * 插件合约禁用请求
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-03-15
 */

@Data
@ApiModel(value = "PluginContractDisableParameter",description = "插件合约禁用请求")
@Accessors(chain = true)
public class PluginContractDisableParameter {
    /**
     * 插件合约编号
     */
     @ApiModelProperty(value = "插件合约编号")
     @LogMetaId
     private Long contractId;
}
