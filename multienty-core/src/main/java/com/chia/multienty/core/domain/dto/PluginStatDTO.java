package com.chia.multienty.core.domain.dto;

import com.chia.multienty.core.pojo.PluginStat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
/**
 * <p>
 * 插件统计 DTO对象
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-03-15
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value="PluginStatDTO", description="插件统计DTO对象")
public class PluginStatDTO extends PluginStat {
}
