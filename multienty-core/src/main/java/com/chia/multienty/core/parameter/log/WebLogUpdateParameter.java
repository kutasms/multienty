package com.chia.multienty.core.parameter.log;

import lombok.Data;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
/**
 * <p>
 * web请求记录更新请求
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-01-06
 */

@Data
@ApiModel(value = "WebLogUpdateParameter",description = "web请求记录更新请求")
public class WebLogUpdateParameter {

        /**
         * 请求记录id
         */
        @ApiModelProperty(value = "请求记录id")
        private Long logId;
        /**
         * 请求用户名
         */
        @ApiModelProperty(value = "请求用户名")
        private String name;
        /**
         * 请求用户ID(根据应用类型分别查询不同表)
         */
        @ApiModelProperty(value = "请求用户ID(根据应用类型分别查询不同表)")
        private Long uid;
        /**
         * 请求接口地址
         */
        @ApiModelProperty(value = "请求接口地址")
        private String url;
        /**
         * 接口名称
         */
        @ApiModelProperty(value = "接口名称")
        private String api;
        /**
         * 请求接口参数
         */
        @ApiModelProperty(value = "请求接口参数")
        private String args;
        /**
         * 请求服务平台
         */
        @ApiModelProperty(value = "请求服务平台")
        private String appName;
        /**
         * IP地址
         */
        @ApiModelProperty(value = "IP地址")
        private String ip;
        /**
         * 描述
         */
        @ApiModelProperty(value = "描述")
        private String description;
        /**
         * 浏览器
         */
        @ApiModelProperty(value = "浏览器")
        private String browser;
        /**
         * 耗时
         */
        @ApiModelProperty(value = "耗时")
        private Long time;
        /**
         * 创建时间
         */
        @ApiModelProperty(value = "创建时间")
        private LocalDateTime createTime;
        /**
         * 关联数据编号
         */
        @ApiModelProperty(value = "关联数据编号")
        private Long metaId;
        /**
         * 关联数据编号(扩展)
         */
        @ApiModelProperty(value = "关联数据编号(扩展)")
        private Long metaId2;
        /**
         * 关联数据编号(扩展)
         */
        @ApiModelProperty(value = "关联数据编号(扩展)")
        private Long metaId3;
        /**
         * 日志类型
         */
        @ApiModelProperty(value = "日志类型")
        private Short type;
}
