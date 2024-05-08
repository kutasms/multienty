package com.chia.multienty.core.parameter.plugin;

import lombok.Data;
import com.chia.multienty.core.pojo.KutaBaseEntity;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotNull;
import lombok.experimental.Accessors;
/**
 * <p>
 * 插件合约详情获取请求
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-03-15
 */

@Data
@Accessors(chain = true)
@ApiModel(value = "PluginContractDetailGetParameter",description = "插件合约详情获取请求")
public class PluginContractDetailGetParameter {
    /**
     * 插件合约编号
     */
     @ApiModelProperty(value = "插件合约编号")
     private Long contractId;
}
