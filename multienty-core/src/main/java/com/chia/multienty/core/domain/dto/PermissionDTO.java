package com.chia.multienty.core.domain.dto;

import com.chia.multienty.core.pojo.Permission;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * <p>
 * 权限菜单信息 DTO对象
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-01-03
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value="PermissionDTO", description="权限菜单信息DTO对象")
public class PermissionDTO extends Permission {
    @ApiModelProperty("角色名称")
    private String roleName;
    @ApiModelProperty("子级")
    private List<PermissionDTO> children;
}
