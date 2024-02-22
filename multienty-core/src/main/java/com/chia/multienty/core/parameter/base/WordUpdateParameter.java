package com.chia.multienty.core.parameter.base;

import lombok.Data;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
/**
 * <p>
 * 关键词信息更新请求
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-01-06
 */

@Data
@ApiModel(value = "WordUpdateParameter",description = "关键词信息更新请求")
public class WordUpdateParameter {

        /**
         * 关键词id
         */
        @ApiModelProperty(value = "关键词id")
        private Long id;
        /**
         * 关键词内容
         */
        @ApiModelProperty(value = "关键词内容")
        private String keyword;
        /**
         * 状态
         */
        @ApiModelProperty(value = "状态")
        private String status;
        /**
         * 创建时间
         */
        @ApiModelProperty(value = "创建时间")
        private LocalDateTime createTime;
        /**
         * 类型  FORBIDDEN - 禁止的 ALLOWED - 允许的
         */
        @ApiModelProperty(value = "类型  FORBIDDEN - 禁止的 ALLOWED - 允许的")
        private String type;
        /**
         * 是否已删除
         */
        @ApiModelProperty(value = "是否已删除")
        private Boolean deleted;
}
