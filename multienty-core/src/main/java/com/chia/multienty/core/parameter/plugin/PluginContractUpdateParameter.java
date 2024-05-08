package com.chia.multienty.core.parameter.plugin;

import lombok.Data;
import com.chia.multienty.core.annotation.LogMetaId;
import com.chia.multienty.core.pojo.KutaBaseEntity;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotNull;
import lombok.experimental.Accessors;
/**
 * <p>
 * 插件合约更新请求
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-03-15
 */

@Data
@ApiModel(value = "PluginContractUpdateParameter",description = "插件合约更新请求")
@Accessors(chain = true)
public class PluginContractUpdateParameter {

    /**
     * 插件合约编号
     */
     @ApiModelProperty(value = "插件合约编号")
     @LogMetaId
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
     * 创建时间
     */
     @ApiModelProperty(value = "创建时间")
     private LocalDateTime createTime;
    /**
     * 状态
     */
     @ApiModelProperty(value = "状态")
     private String status;
    /**
     * 乐观锁版本号
     */
     @ApiModelProperty(value = "乐观锁版本号")
     private Long version;
}
