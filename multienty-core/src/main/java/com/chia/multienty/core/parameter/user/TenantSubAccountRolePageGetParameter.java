package com.chia.multienty.core.parameter.user;

import com.chia.multienty.core.domain.dto.TenantSubAccountRoleDTO;
import com.chia.multienty.core.mybatis.DefaultListGetParameter;
import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;
/**
 * <p>
 * 租户子账号角色关联分页列表查询请求
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-02-01
 */

@Data
@ApiModel(value = "TenantSubAccountRolePageGetParameter",description = "租户子账号角色关联分页列表查询请求")
public class TenantSubAccountRolePageGetParameter extends DefaultListGetParameter<TenantSubAccountRoleDTO> {

    /**
     * 关联编号
     */
     @ApiModelProperty(value = "关联编号列表")
     private List<Long> sarIds;
}
