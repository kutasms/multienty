package com.chia.multienty.core.parameter.plugin;

import lombok.Data;
import com.chia.multienty.core.mybatis.DefaultListGetParameter;
import com.chia.multienty.core.domain.dto.PluginPriceStrategyDTO;
import com.chia.multienty.core.pojo.KutaBaseEntity;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotNull;
import java.util.List;
import lombok.experimental.Accessors;
/**
 * <p>
 * 插件价格策略分页列表查询请求
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-03-15
 */

@Data
@ApiModel(value = "PluginPriceStrategyPageGetParameter",description = "插件价格策略分页列表查询请求")
@Accessors(chain = true)
public class PluginPriceStrategyPageGetParameter extends DefaultListGetParameter<PluginPriceStrategyDTO>{

    /**
     * 插件价格策略编号
     */
     @ApiModelProperty(value = "插件价格策略编号列表")
     private List<Long> strategyIds;
}
