package com.chia.multienty.core.parameter.user;

import lombok.Data;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
/**
 * <p>
 * 角色信息更新请求
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-01-06
 */

@Data
@ApiModel(value = "RoleUpdateParameter",description = "角色信息更新请求")
public class RoleUpdateParameter {

        /**
         * 角色id
         */
        @ApiModelProperty(value = "角色id")
        private Long roleId;
        /**
         * 角色名称
         */
        @ApiModelProperty(value = "角色名称")
        private String name;
        /**
         * 别名
         */
        @ApiModelProperty(value = "别名")
        private String alias;
        /**
         * 角色描述
         */
        @ApiModelProperty(value = "角色描述")
        private String description;
        /**
         * 拥有者
         */
        @ApiModelProperty(value = "拥有者")
        private Long owner;
        /**
         * 可编辑
         */
        @ApiModelProperty(value = "可编辑")
        private Boolean editable;
        /**
         * 应用id
         */
        @ApiModelProperty(value = "应用id")
        private Byte appId;
        /**
         * 乐观锁
         */
        @ApiModelProperty(value = "乐观锁")
        private Long version;
        /**
         * 可删除
         */
        @ApiModelProperty(value = "可删除")
        private Boolean deletable;
        /**
         * 是否超级管理员
         */
        @ApiModelProperty(value = "是否超级管理员")
        private Boolean superAdmin;
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
         * 是否已删除
         */
        @ApiModelProperty(value = "是否已删除")
        private Boolean deleted;
}
