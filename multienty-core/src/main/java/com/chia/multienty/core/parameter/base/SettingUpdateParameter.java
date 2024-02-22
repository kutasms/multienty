package com.chia.multienty.core.parameter.base;

import lombok.Data;
import com.chia.multienty.core.annotation.LogMetaId;

import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.experimental.Accessors;
/**
 * <p>
 * 系统配置更新请求
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-02-16
 */

@Data
@ApiModel(value = "SettingUpdateParameter",description = "系统配置更新请求")
@Accessors(chain = true)
public class SettingUpdateParameter {

    /**
     * 配置编号
     */
     @ApiModelProperty(value = "配置编号")
     @LogMetaId
     private Long settingId;
    /**
     * 名称
     */
     @ApiModelProperty(value = "名称")
     private String name;
    /**
     * 值
     */
     @ApiModelProperty(value = "值")
     private String value;
    /**
     * 默认值
     */
     @ApiModelProperty(value = "默认值")
     private String defaultValue;
    /**
     * 创建时间
     */
     @ApiModelProperty(value = "创建时间")
     private LocalDateTime createTime;
    /**
     * 拥有者
     */
     @ApiModelProperty(value = "拥有者")
     private Long owner;
    /**
     * 应用编号
     */
     @ApiModelProperty(value = "应用编号")
     private Integer appId;
}
