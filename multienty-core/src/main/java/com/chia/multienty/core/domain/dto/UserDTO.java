package com.chia.multienty.core.domain.dto;

import com.chia.multienty.core.domain.basic.IWebLogUser;
import com.chia.multienty.core.domain.enums.StatusEnum;
import com.chia.multienty.core.pojo.User;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 管理账户信息 DTO对象
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-01-03
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value="UserDTO", description="管理账户信息DTO对象")
public class UserDTO extends User implements IWebLogUser {
    @ApiModelProperty("角色名称")
    private String roleName;
    @ApiModelProperty("角色编号列表")
    private String roleIdStrings;
    @ApiModelProperty("角色别名")
    private String roleAlias;

    @ApiModelProperty("权限列表")
    private List<PermissionDTO> permissions;

    /**
     * 是否已禁用
     * */
    @ApiModelProperty("是否已禁用")
    public boolean disabled() {
        return this.getStatus().equals(StatusEnum.DISABLED.getCode());
    }

    @Override
    public String getUserName() {
        return getName();
    }

    public List<Long> getRoleIds() {
        if(this.roleIdStrings == null) {
            return null;
        }
        String[] split = this.roleIdStrings.split(",");
        return Arrays.stream(split).map(m->Long.parseLong(m)).collect(Collectors.toList());
    }

    /**
     * 是否超级管理员
     */
    @ApiModelProperty(value = "是否超级管理员")
    private Boolean superAdmin;


}
