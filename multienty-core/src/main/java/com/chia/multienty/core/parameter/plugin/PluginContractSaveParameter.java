package com.chia.multienty.core.parameter.plugin;

import lombok.Data;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.chia.multienty.core.annotation.LogMetaId;
import com.chia.multienty.core.pojo.KutaBaseEntity;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotNull;
import lombok.experimental.Accessors;
/**
 * <p>
 * 插件合约保存请求
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-03-15
 */

@Data
@ApiModel(value = "PluginContractSaveParameter",description = "插件合约保存请求")
@Accessors(chain = true)
public class PluginContractSaveParameter {

    /**
     * 插件合约编号
     */
    @ApiModelProperty(value = "插件合约编号")
    @LogMetaId
    @JsonIgnore
    @JsonProperty(required = false)
    private Long contractId;
    /**
     * 插件编号
     */
    @ApiModelProperty(value = "插件编号")
    private Long pluginId;
    /**
     * 截至时间
     */
    @ApiModelProperty(value = "截至时间")
    private LocalDateTime deadline;
    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;
}
