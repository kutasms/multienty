package com.chia.multienty.core.domain.dto;

import com.chia.multienty.core.pojo.PluginPriceStrategy;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
/**
 * <p>
 * 插件价格策略 DTO对象
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-03-15
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value="PluginPriceStrategyDTO", description="插件价格策略DTO对象")
public class PluginPriceStrategyDTO extends PluginPriceStrategy {
}
