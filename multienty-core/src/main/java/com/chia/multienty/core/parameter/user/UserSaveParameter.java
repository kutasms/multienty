package com.chia.multienty.core.parameter.user;

import com.chia.multienty.core.domain.basic.IWebLogUser;
import com.chia.multienty.core.pojo.Role;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
/**
 * <p>
 * 管理账户信息保存请求
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-01-06
 */

@Data
@ApiModel(value = "UserSaveParameter",description = "管理账户信息保存请求")
public class UserSaveParameter implements IWebLogUser {

        /**
         * 用户编号
         */
        @ApiModelProperty(value = "用户编号")
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
         * 更新时间
         */
        @ApiModelProperty(value = "更新时间")
        private LocalDateTime updateTime;

        /**
         * 是否已过期
         */
        @ApiModelProperty(value = "是否已过期")
        private Boolean expired;

        /**
         * 是否已锁定
         */
        @ApiModelProperty(value = "是否已锁定")
        private Boolean locked;

        private Role role;
        private List<Long> roleIds;

        @Override
        public Long getLogUserId() {
                return userId;
        }

        @Override
        public String getLogUserName() {
                return name;
        }
}
