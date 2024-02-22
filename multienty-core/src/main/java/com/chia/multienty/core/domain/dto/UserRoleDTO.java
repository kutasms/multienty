package com.chia.multienty.core.domain.dto;

import com.chia.multienty.core.pojo.UserRole;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
/**
 * <p>
 * 账户关联角色信息表 DTO对象
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-01-03
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value="UserRoleDTO", description="账户关联角色信息表DTO对象")
public class UserRoleDTO extends UserRole {
    private String roleName;
}
