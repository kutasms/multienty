package com.chia.multienty.core.domain.dto;

import com.chia.multienty.core.pojo.Tenant;
import com.chia.multienty.core.domain.basic.IWebLogUser;
import com.chia.multienty.core.domain.enums.StatusEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 租户信息 DTO对象
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-01-03
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value="TenantDTO", description="租户信息DTO对象")
public class TenantDTO extends Tenant implements IWebLogUser {
    @ApiModelProperty("角色名称")
    private String roleName;
    @ApiModelProperty("角色编号列表")
    private String roleIdStrings;
    @ApiModelProperty("角色别名")
    private String roleAlias;

    /**
     * 是否已禁用
     * */
    @ApiModelProperty("是否已禁用")
    public boolean disabled() {
        return this.getStatus().equals(StatusEnum.DISABLED.getCode());
    }

    @Override
    public Long getUserId() {
        return getTenantId();
    }

    @Override
    public String getUserName() {
        return getCompanyName();
    }

    public List<Long> getRoleIds() {
        if(this.roleIdStrings == null) {
            return null;
        }
        String[] split = this.roleIdStrings.split(",");
        return Arrays.stream(split).map(m->Long.parseLong(m)).collect(Collectors.toList());
    }
}
