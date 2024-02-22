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
 * Json配置保存请求
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-01-10
 */

@Data
@ApiModel(value = "JsonConfigSaveParameter",description = "Json配置保存请求")
public class JsonConfigSaveParameter {

    /**
     * 配置编号
     */
    @ApiModelProperty(value = "配置编号")
    @LogMetaId
    @JsonIgnore
    @JsonProperty(required = false)
    private Long configId;
    /**
     * 别名
     */
    @ApiModelProperty(value = "别名")
    private String alias;
    /**
     * 说明
     */
    @ApiModelProperty(value = "说明")
    private String remark;
    /**
     * JSON数据
     */
    @ApiModelProperty(value = "JSON数据")
    private String data;
    /**
     * 默认数据
     */
    @ApiModelProperty(value = "默认数据")
    private String defaultData;
    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;
}
