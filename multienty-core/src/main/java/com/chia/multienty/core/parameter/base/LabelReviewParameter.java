package com.chia.multienty.core.parameter.base;

import com.chia.multienty.core.domain.enums.StatusEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.jetbrains.annotations.NotNull;

/**
 * <p>
 * 标签审核请求
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-01-06
 */

@Data
@ApiModel(value = "LabelReviewParameter",description = "标签审核请求")
public class LabelReviewParameter {
    /**
     * 标签编号
     */
    @ApiModelProperty("标签编号")
    @NotNull
    private Long labelId;
    /**
     * 状态
     */
    @ApiModelProperty("状态")
    private StatusEnum status;

    /**
     * 审核失败原因
     */
    @ApiModelProperty("审核失败原因")
    private String failReason;
}
