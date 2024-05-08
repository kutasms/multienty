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
 * 插件资源详情获取请求
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-03-16
 */

@Data
@Accessors(chain = true)
@ApiModel(value = "PluginResDetailGetParameter",description = "插件资源详情获取请求")
public class PluginResDetailGetParameter {
    /**
     * 插件资源编号
     */
     @ApiModelProperty(value = "插件资源编号")
     private Long resId;
}
