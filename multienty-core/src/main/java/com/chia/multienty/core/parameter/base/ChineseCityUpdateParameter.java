package com.chia.multienty.core.parameter.base;

import lombok.Data;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
/**
 * <p>
 * 城市管理信息表更新请求
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-01-06
 */

@Data
@ApiModel(value = "ChineseCityUpdateParameter",description = "城市管理信息表更新请求")
public class ChineseCityUpdateParameter {

        /**
         * 城市id
         */
        @ApiModelProperty(value = "城市id")
        private Long cityId;
        /**
         * 上级节点id
         */
        @ApiModelProperty(value = "上级节点id")
        private Long cityPid;
        /**
         * 城市名称
         */
        @ApiModelProperty(value = "城市名称")
        private String cityName;
        /**
         * 创建时间
         */
        @ApiModelProperty(value = "创建时间")
        private LocalDateTime createTime;
}
