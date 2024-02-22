package com.chia.multienty.core.parameter.base;

import lombok.Data;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
/**
 * <p>
 * 存储文件更新请求
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-01-06
 */

@Data
@ApiModel(value = "UploadFileUpdateParameter",description = "存储文件更新请求")
public class UploadedFileUpdateParameter {

        /**
         * 文件编号
         */
        @ApiModelProperty(value = "文件编号")
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
         * 元编号
         */
        @ApiModelProperty(value = "元编号")
        private Long metaId;
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
         * 原始文件名
         */
        @ApiModelProperty(value = "原始文件名")
        private String orgFileName;
}
