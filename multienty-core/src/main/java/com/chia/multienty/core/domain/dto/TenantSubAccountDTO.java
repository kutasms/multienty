package com.chia.multienty.core.domain.dto;

import com.chia.multienty.core.pojo.TenantSubAccount;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * <p>
 * 余额账单 DTO对象
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-02-01
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value="TenantSubAccountDTO", description="余额账单DTO对象")
public class TenantSubAccountDTO extends TenantSubAccount {
    @ApiModelProperty("角色名称")
    private String roleName;
    @ApiModelProperty("角色编号列表")
    private String roleIdStrings;
    @ApiModelProperty("角色别名")
    private String roleAlias;

    private List<Long> roleIds;
    @ApiModelProperty("权限集合")
    private List<PermissionDTO> permissions;

    private Boolean isSuperAdmin = false;
}
