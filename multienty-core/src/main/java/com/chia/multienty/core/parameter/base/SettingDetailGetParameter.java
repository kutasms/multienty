package com.chia.multienty.core.parameter.base;

import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.experimental.Accessors;
/**
 * <p>
 * 系统配置详情获取请求
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-02-16
 */

@Data
@Accessors(chain = true)
@ApiModel(value = "SettingDetailGetParameter",description = "系统配置详情获取请求")
public class SettingDetailGetParameter {
    /**
     * 配置编号
     */
     @ApiModelProperty(value = "配置编号")
     private Long settingId;
}
