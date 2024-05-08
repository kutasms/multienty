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
 * 插件资源删除请求
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-03-16
 */

@Data
@ApiModel(value = "PluginResDeleteParameter",description = "插件资源删除请求")
@Accessors(chain = true)
public class PluginResDeleteParameter {

    /**
     * 插件资源编号
     */
    @ApiModelProperty(value = "插件资源编号")
    @LogMetaId
    private Long resId;

}
