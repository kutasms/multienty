package com.chia.multienty.core.parameter.base;

import lombok.Data;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.chia.multienty.core.annotation.LogMetaId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
/**
 * <p>
 * 通知保存请求
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-01-10
 */

@Data
@ApiModel(value = "NoticeSaveParameter",description = "通知保存请求")
public class NoticeSaveParameter {

    /**
     * 通知编号
     */
    @ApiModelProperty(value = "通知编号")
    @LogMetaId
    @JsonIgnore
    @JsonProperty(required = false)
    private Long noticeId;
    /**
     * 通知类型
     */
    @ApiModelProperty(value = "通知类型")
    private Byte type;
    /**
     * 跳转类型
     */
    @ApiModelProperty(value = "跳转类型")
    private Byte jumpMode;
    /**
     * 接收者类型
     */
    @ApiModelProperty(value = "接收者类型")
    private Byte receiverType;
    /**
     * 接收者id
     */
    @ApiModelProperty(value = "接收者id")
    private Long userId;
    /**
     * 标题
     */
    @ApiModelProperty(value = "标题")
    private String subject;
    /**
     * 通知内容
     */
    @ApiModelProperty(value = "通知内容")
    private String message;
    /**
     * 跳转元标识
     */
    @ApiModelProperty(value = "跳转元标识")
    private Long jumpMetaId;
    /**
     * 图片
     */
    @ApiModelProperty(value = "图片")
    private String mainImage;
}
