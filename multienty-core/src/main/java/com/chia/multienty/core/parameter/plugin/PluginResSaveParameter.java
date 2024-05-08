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
 * 插件资源保存请求
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-03-16
 */

@Data
@ApiModel(value = "PluginResSaveParameter",description = "插件资源保存请求")
@Accessors(chain = true)
public class PluginResSaveParameter {

    /**
     * 插件资源编号
     */
    @ApiModelProperty(value = "插件资源编号")
    @LogMetaId
    @JsonIgnore
    @JsonProperty(required = false)
    private Long resId;
    /**
     * 插件编号
     */
    @ApiModelProperty(value = "插件编号")
    private Long pluginId;
    /**
     * 资源URL地址
     */
    @ApiModelProperty(value = "资源URL地址")
    private String resUrl;
    /**
     * 文件名
     */
    @ApiModelProperty(value = "文件名")
    private String fileName;
    /**
     * 文件编号
     */
    @ApiModelProperty(value = "文件编号")
    private Long fileId;
}
