package com.chia.multienty.core.parameter.base;

import com.chia.multienty.core.annotation.LogMetaId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;
/**
 * <p>
 * Json格式配置信息更新请求
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-01-06
 */

@Data
@ApiModel(value = "JsonConfigUpdateParameter",description = "Json格式配置信息更新请求")
public class JsonConfigUpdateParameter {
        @LogMetaId
        private Long configId;
        /**
         * 别名
         */
        @ApiModelProperty(value = "别名")
        private String alias;
        /**
         * 说明
         */
        @ApiModelProperty(value = "说明")
        private String remark;
        /**
         * JSON数据
         */
        @ApiModelProperty(value = "JSON数据")
        private String data;
        /**
         * 默认数据
         */
        @ApiModelProperty(value = "默认数据")
        private String defaultData;
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
}
