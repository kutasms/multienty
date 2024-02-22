package com.chia.multienty.core.parameter.user;

import com.chia.multienty.core.pojo.Role;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
/**
 * <p>
 * 管理账户信息更新请求
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-01-06
 */

@Data
@ApiModel(value = "UserUpdateParameter",description = "管理账户信息更新请求")
public class UserUpdateParameter {

        /**
         * 账户id
         */
        @ApiModelProperty(value = "账户id")
        private Long userId;
        /**
         * 登录账号
         */
        @ApiModelProperty(value = "登录账号")
        private String username;
        /**
         * 昵称
         */
        @ApiModelProperty(value = "昵称")
        private String name;
        /**
         * 性别
         */
        @ApiModelProperty(value = "性别")
        private Byte sex;
        /**
         * 账户手机号
         */
        @ApiModelProperty(value = "账户手机号")
        private String phone;
        /**
         * 账户登录密码
         */
        @ApiModelProperty(value = "账户登录密码")
        private String password;
        /**
         * 应用id
         */
        @ApiModelProperty(value = "应用id")
        private Byte appId;
        /**
         * 邮箱
         */
        @ApiModelProperty(value = "邮箱")
        private String email;
        /**
         * 账户token
         */
        @ApiModelProperty(value = "账户token")
        private String token;
        /**
         * 头像地址
         */
        @ApiModelProperty(value = "头像地址")
        private String avatar;
        /**
         * 乐观锁
         */
        @ApiModelProperty(value = "乐观锁")
        private Long version;
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

        private Role role;
        private List<Long> roleIds;
}
