package com.chia.multienty.core.pojo;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * <p>
 * 已上传文件
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-04-10
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("mt_uploaded_file")
@ApiModel(value="UploadedFile对象", description="已上传文件")
public class UploadedFile extends KutaBaseEntity {


    /**
     * 文件编号
     */
    @ApiModelProperty(value = "文件编号")
    @TableId(value = "file_id", type = IdType.AUTO)
    private Long fileId;

    /**
     * 文件路径
     */
    @ApiModelProperty(value = "文件路径")
    @TableField("`path`")
    private String path;

    /**
     * 文件URL地址
     */
    @ApiModelProperty(value = "文件URL地址")
    @TableField("`url`")
    private String url;

    /**
     * 文件类型
     */
    @ApiModelProperty(value = "文件类型")
    @TableField("`type`")
    private Byte type;

    /**
     * 文件名称
     */
    @ApiModelProperty(value = "文件名称")
    @TableField("`name`")
    private String name;

    /**
     * 文件扩展名
     */
    @ApiModelProperty(value = "文件扩展名")
    @TableField("`extension`")
    private String extension;

    /**
     * 关联编号
     */
    @ApiModelProperty(value = "关联编号")
    @TableField("`meta_id`")
    private Long metaId;

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
     * 原始文件名
     */
    @ApiModelProperty(value = "原始文件名")
    @TableField("`org_file_name`")
    private String orgFileName;


}
