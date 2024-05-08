package com.chia.multienty.core.parameter.plugin;

import lombok.Data;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
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
 * 插件价格策略保存请求
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-03-15
 */

@Data
@ApiModel(value = "PluginPriceStrategySaveParameter",description = "插件价格策略保存请求")
@Accessors(chain = true)
public class PluginPriceStrategySaveParameter {

    /**
     * 插件价格策略编号
     */
    @ApiModelProperty(value = "插件价格策略编号")
    @LogMetaId
    @JsonIgnore
    @JsonProperty(required = false)
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
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;
}
