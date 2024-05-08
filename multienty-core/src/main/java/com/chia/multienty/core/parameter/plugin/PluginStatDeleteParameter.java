package com.chia.multienty.core.parameter.plugin;

import lombok.Data;
import com.chia.multienty.core.annotation.LogMetaId;
import javax.validation.constraints.NotNull;
import com.chia.multienty.core.pojo.KutaBaseEntity;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.experimental.Accessors;
/**
 * <p>
 * 插件统计删除请求
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-03-15
 */

@Data
@ApiModel(value = "PluginStatDeleteParameter",description = "插件统计删除请求")
@Accessors(chain = true)
public class PluginStatDeleteParameter {

    /**
     * 插件编号
     */
    @ApiModelProperty(value = "插件编号")
    @LogMetaId
    private Long pluginId;

}
