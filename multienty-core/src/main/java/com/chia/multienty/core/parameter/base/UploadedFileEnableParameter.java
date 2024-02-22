package com.chia.multienty.core.parameter.base;

import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
/**
 * <p>
 * 已上传文件启用请求
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-02-01
 */

@Data
@ApiModel(value = "UploadedFileEnableParameter",description = "已上传文件启用请求")
public class UploadedFileEnableParameter {
    /**
     * 文件编号
     */
     @ApiModelProperty(value = "文件编号")
     private Long fileId;
}
