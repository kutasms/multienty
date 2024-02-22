package com.chia.multienty.core.parameter.user;

import lombok.Data;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
/**
 * <p>
 * 权限菜单信息更新请求
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-01-06
 */

@Data
@ApiModel(value = "PermissionUpdateParameter",description = "权限菜单信息更新请求")
public class PermissionUpdateParameter {

        /**
         * 权限id
         */
        @ApiModelProperty(value = "权限id")
        private Long permissionId;
        /**
         * 父节点id
         */
        @ApiModelProperty(value = "父节点id")
        private Long pid;
        /**
         * 名称
         */
        @ApiModelProperty(value = "名称")
        private String name;
        /**
         * 路径
         */
        @ApiModelProperty(value = "路径")
        private String path;
        /**
         * 组件
         */
        @ApiModelProperty(value = "组件")
        private String component;
        /**
         * 图标地址
         */
        @ApiModelProperty(value = "图标地址")
        private String icon;
        /**
         * 文字描述
         */
        @ApiModelProperty(value = "文字描述")
        private String description;
        /**
         * 拥有者
         */
        @ApiModelProperty(value = "拥有者")
        private Long owner;
        /**
         * 权限类型
         */
        @ApiModelProperty(value = "权限类型")
        private Byte type;
        /**
         * 排序号
         */
        @ApiModelProperty(value = "排序号")
        private Integer sort;
        /**
         * 应用id
         */
        @ApiModelProperty(value = "应用id")
        private Byte appId;
        /**
         * 是否隐藏菜单
         */
        @ApiModelProperty(value = "是否隐藏菜单")
        private Boolean hidden;
        /**
         * 重定向
         */
        @ApiModelProperty(value = "重定向")
        private String redirect;
        /**
         * 别名
         */
        @ApiModelProperty(value = "别名")
        private String alias;
        /**
         * 是否固定到导航条
         */
        @ApiModelProperty(value = "是否固定到导航条")
        private Boolean affix;
        /**
         * 层级
         */
        @ApiModelProperty(value = "层级")
        private String hierarchy;
        /**
         * 创建时间
         */
        @ApiModelProperty(value = "创建时间")
        private LocalDateTime createTime;
        /**
         * 乐观锁版本号
         */
        @ApiModelProperty(value = "乐观锁版本号")
        private Long version;
        /**
         * 状态
         */
        @ApiModelProperty(value = "状态")
        private String status;
        /**
         * 是否已删除
         */
        @ApiModelProperty(value = "是否已删除")
        private Boolean deleted;
}
