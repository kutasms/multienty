package com.chia.multienty.core.parameter.base;

import lombok.Data;
import com.chia.multienty.core.annotation.LogMetaId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.experimental.Accessors;
/**
 * <p>
 * 系统配置删除请求
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-02-16
 */

@Data
@ApiModel(value = "SettingDeleteParameter",description = "系统配置删除请求")
@Accessors(chain = true)
public class SettingDeleteParameter {

    /**
     * 配置编号
     */
    @ApiModelProperty(value = "配置编号")
    @LogMetaId
    private Long settingId;

}
