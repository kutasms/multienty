package com.chia.multienty.core.domain.dto;

import com.chia.multienty.core.pojo.PluginContract;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
/**
 * <p>
 * 插件合约 DTO对象
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-03-15
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value="PluginContractDTO", description="插件合约DTO对象")
public class PluginContractDTO extends PluginContract {
}
