package com.chia.multienty.core.domain.dto;

import com.chia.multienty.core.pojo.Role;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * <p>
 * 角色信息 DTO对象
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-01-03
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value="RoleDTO", description="角色信息DTO对象")
public class RoleDTO extends Role {
    @ApiModelProperty("权限编号列表")
    private List<String> permissionIds;
    @ApiModelProperty("逗号分隔的权限组合字符串")
    private String permissionJoinedStr;
}
