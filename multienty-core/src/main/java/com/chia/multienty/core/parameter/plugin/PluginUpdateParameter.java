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
 * 插件更新请求
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-03-15
 */

@Data
@ApiModel(value = "PluginUpdateParameter",description = "插件更新请求")
@Accessors(chain = true)
public class PluginUpdateParameter {

    /**
     * 插件编号
     */
     @ApiModelProperty(value = "插件编号")
     @LogMetaId
     private Long pluginId;
    /**
     * 插件名称
     */
     @ApiModelProperty(value = "插件名称")
     private String name;
    /**
     * 插件类型
     */
     @ApiModelProperty(value = "插件类型")
     private Short type;
    /**
     * 插件类型名称
     */
     @ApiModelProperty(value = "插件类型名称")
     private String typeName;
    /**
     * 别名
     */
     @ApiModelProperty(value = "别名")
     private String alias;
    /**
     * 详情
     */
     @ApiModelProperty(value = "详情")
     private String details;
    /**
     * 运行模式
     */
     @ApiModelProperty(value = "运行模式")
     private Byte runningMode;
    /**
     * 来源
     */
     @ApiModelProperty(value = "来源")
     private String source;
    /**
     * 运行状态
     */
     @ApiModelProperty(value = "运行状态")
     private String runningState;
    /**
     * 库名称
     */
     @ApiModelProperty(value = "库名称")
     private String libName;
    /**
     * 包名称
     */
     @ApiModelProperty(value = "包名称")
     private String packageName;
    /**
     * 类名称
     */
     @ApiModelProperty(value = "类名称")
     private String className;
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
