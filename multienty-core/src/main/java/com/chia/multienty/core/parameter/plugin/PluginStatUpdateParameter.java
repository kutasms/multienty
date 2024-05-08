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
 * 插件统计更新请求
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-03-15
 */

@Data
@ApiModel(value = "PluginStatUpdateParameter",description = "插件统计更新请求")
@Accessors(chain = true)
public class PluginStatUpdateParameter {

    /**
     * 插件编号
     */
     @ApiModelProperty(value = "插件编号")
     @LogMetaId
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
     * 创建时间
     */
     @ApiModelProperty(value = "创建时间")
     private LocalDateTime createTime;
    /**
     * 乐观锁版本号
     */
     @ApiModelProperty(value = "乐观锁版本号")
     private Long version;
}
