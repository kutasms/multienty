package com.chia.multienty.core.pojo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.chia.multienty.core.pojo.KutaBaseEntity;
import java.time.LocalDateTime;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 通知
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-03-08
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("mt_notice")
@ApiModel(value="Notice对象", description="通知")
public class Notice extends KutaBaseEntity {


    /**
     * 通知编号
     */
    @ApiModelProperty(value = "通知编号")
    @TableId(value = "notice_id", type = IdType.AUTO)
    private Long noticeId;

    /**
     * 通知类型
     */
    @ApiModelProperty(value = "通知类型")
    @TableField("`type`")
    private Byte type;

    /**
     * 跳转类型
     */
    @ApiModelProperty(value = "跳转类型")
    @TableField("`jump_mode`")
    private Byte jumpMode;

    /**
     * 接收者类型
     */
    @ApiModelProperty(value = "接收者类型")
    @TableField("`receiver_type`")
    private Byte receiverType;

    /**
     * 接收者id
     */
    @ApiModelProperty(value = "接收者id")
    @TableField("`user_id`")
    private Long userId;

    /**
     * 标题
     */
    @ApiModelProperty(value = "标题")
    @TableField("`subject`")
    private String subject;

    /**
     * 通知内容
     */
    @ApiModelProperty(value = "通知内容")
    @TableField("`message`")
    private String message;

    /**
     * 跳转元标识
     */
    @ApiModelProperty(value = "跳转元标识")
    @TableField("`jump_meta_id`")
    private Long jumpMetaId;

    /**
     * 图片
     */
    @ApiModelProperty(value = "图片")
    @TableField("`main_image`")
    private String mainImage;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    @TableField(value = "`create_time`", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 状态
     */
    @ApiModelProperty(value = "状态")
    @TableField(value = "`status`", fill = FieldFill.INSERT)
    private String status;

    /**
     * 是否已删除
     */
    @ApiModelProperty(value = "是否已删除")
    @TableField(value = "`deleted`", fill = FieldFill.INSERT)
    @TableLogic
    private Boolean deleted;


}
