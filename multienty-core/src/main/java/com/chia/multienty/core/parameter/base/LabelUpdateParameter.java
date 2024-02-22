package com.chia.multienty.core.parameter.base;

import lombok.Data;
import com.chia.multienty.core.annotation.LogMetaId;

import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
/**
 * <p>
 * 标签更新请求
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-01-30
 */

@Data
@ApiModel(value = "LabelUpdateParameter",description = "标签更新请求")
public class LabelUpdateParameter {

    /**
     * 标签编号
     */
     @ApiModelProperty(value = "标签编号")
     @LogMetaId
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
     * 创建时间
     */
     @ApiModelProperty(value = "创建时间")
     private LocalDateTime createTime;
    /**
     * 状态
     */
     @ApiModelProperty(value = "状态")
     private String status;
    /**
     * 是否已删除
     */
     @ApiModelProperty(value = "是否已删除")
     private Boolean deleted;
    /**
     * 审核失败原因
     */
     @ApiModelProperty(value = "审核失败原因")
     private String failReason;
}
