package com.chia.multienty.core.parameter.plugin;

import lombok.Data;
import com.chia.multienty.core.annotation.LogMetaId;
import com.chia.multienty.core.pojo.KutaBaseEntity;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotNull;
import lombok.experimental.Accessors;
/**
 * <p>
 * 插件价格策略更新请求
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-03-15
 */

@Data
@ApiModel(value = "PluginPriceStrategyUpdateParameter",description = "插件价格策略更新请求")
@Accessors(chain = true)
public class PluginPriceStrategyUpdateParameter {

    /**
     * 插件价格策略编号
     */
     @ApiModelProperty(value = "插件价格策略编号")
     @LogMetaId
     private Long strategyId;
    /**
     * 插件编号
     */
     @ApiModelProperty(value = "插件编号")
     private Long pluginId;
    /**
     * 价格
     */
     @ApiModelProperty(value = "价格")
     private BigDecimal price;
    /**
     * 作用域
     */
     @ApiModelProperty(value = "作用域")
     private String scope;
    /**
     * 是否免费
     */
     @ApiModelProperty(value = "是否免费")
     private Boolean isFree;
    /**
     * 可试用
     */
     @ApiModelProperty(value = "可试用")
     private Boolean triable;
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
