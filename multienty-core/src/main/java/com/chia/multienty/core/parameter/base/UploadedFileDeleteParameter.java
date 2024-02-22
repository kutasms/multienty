package com.chia.multienty.core.parameter.base;

import lombok.Data;
import com.chia.multienty.core.annotation.LogMetaId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
/**
 * <p>
 * 已上传文件删除请求
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-02-01
 */

@Data
@ApiModel(value = "UploadedFileDeleteParameter",description = "已上传文件删除请求")
public class UploadedFileDeleteParameter {

    /**
     * 文件编号
     */
     @ApiModelProperty(value = "文件编号")
     @LogMetaId
     private Long fileId;
}
