package com.chia.multienty.core.parameter.base;

import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
/**
 * <p>
 * 存储文件详情获取请求
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-01-06
 */

@Data
@ApiModel(value = "UploadFileDetailGetParameter",description = "存储文件详情获取请求")
public class UploadedFileDetailGetParameter {
    /**
     * 文件编号
     */
     @ApiModelProperty(value = "文件编号")
     private Long fileId;
}
