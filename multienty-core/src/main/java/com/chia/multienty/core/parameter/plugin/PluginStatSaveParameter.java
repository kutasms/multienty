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
 * 插件统计保存请求
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-03-15
 */

@Data
@ApiModel(value = "PluginStatSaveParameter",description = "插件统计保存请求")
@Accessors(chain = true)
public class PluginStatSaveParameter {

    /**
     * 插件编号
     */
    @ApiModelProperty(value = "插件编号")
    @LogMetaId
    @JsonIgnore
    @JsonProperty(required = false)
    private Long pluginId;
    /**
     * 点赞数
     */
    @ApiModelProperty(value = "点赞数")
    private Integer starCount;
    /**
     * 关注人数
     */
    @ApiModelProperty(value = "关注人数")
    private Integer watchCount;
    /**
     * 应用次数
     */
    @ApiModelProperty(value = "应用次数")
    private Integer applyCount;
    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;
}
