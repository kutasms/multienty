package com.chia.multienty.core.parameter.base;

import lombok.Data;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.chia.multienty.core.annotation.LogMetaId;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
/**
 * <p>
 * 中国城市保存请求
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-01-10
 */

@Data
@ApiModel(value = "ChineseCitySaveParameter",description = "中国城市保存请求")
public class ChineseCitySaveParameter {

    /**
     * 城市编号
     */
    @ApiModelProperty(value = "城市编号")
    @LogMetaId
    @JsonIgnore
    @JsonProperty(required = false)
    private Long cityId;
    /**
     * 父级编号
     */
    @ApiModelProperty(value = "父级编号")
    private Long cityPid;
    /**
     * 城市名称
     */
    @ApiModelProperty(value = "城市名称")
    private String cityName;
    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;
}
