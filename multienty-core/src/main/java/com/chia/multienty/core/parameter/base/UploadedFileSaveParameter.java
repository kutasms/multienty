package com.chia.multienty.core.parameter.base;

import lombok.Data;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.chia.multienty.core.annotation.LogMetaId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
/**
 * <p>
 * 已上传文件保存请求
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-01-10
 */

@Data
@ApiModel(value = "UploadFileSaveParameter",description = "已上传文件保存请求")
public class UploadedFileSaveParameter {

    /**
     * 文件编号
     */
    @ApiModelProperty(value = "文件编号")
    @LogMetaId
    @JsonIgnore
    @JsonProperty(required = false)
    private Long fileId;
    /**
     * 文件路径
     */
    @ApiModelProperty(value = "文件路径")
    private String path;
    /**
     * 文件URL地址
     */
    @ApiModelProperty(value = "文件URL地址")
    private String url;
    /**
     * 文件类型
     */
    @ApiModelProperty(value = "文件类型")
    private Byte type;
    /**
     * 文件名称
     */
    @ApiModelProperty(value = "文件名称")
    private String name;
    /**
     * 文件扩展名
     */
    @ApiModelProperty(value = "文件扩展名")
    private String extension;
    /**
     * 关联编号
     */
    @ApiModelProperty(value = "关联编号")
    private Long metaId;
    /**
     * 原始文件名
     */
    @ApiModelProperty(value = "原始文件名")
    private String orgFileName;
}
