package com.chia.multienty.core.parameter.base;

import lombok.Data;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.chia.multienty.core.annotation.LogMetaId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
/**
 * <p>
 * 标签保存请求
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-01-30
 */

@Data
@ApiModel(value = "LabelSaveParameter",description = "标签保存请求")
public class LabelSaveParameter {

    /**
     * 标签编号
     */
    @ApiModelProperty(value = "标签编号")
    @LogMetaId
    @JsonIgnore
    @JsonProperty(required = false)
    private Long labelId;
    /**
     * 标签
     */
    @ApiModelProperty(value = "标签")
    private String label;
    /**
     * 类型
     */
    @ApiModelProperty(value = "类型")
    private Short type;
    /**
     * 审核失败原因
     */
    @ApiModelProperty(value = "审核失败原因")
    private String failReason;
}
