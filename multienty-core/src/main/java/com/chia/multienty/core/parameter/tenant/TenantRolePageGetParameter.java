package com.chia.multienty.core.parameter.tenant;

import com.chia.multienty.core.domain.dto.TenantRoleDTO;
import com.chia.multienty.core.mybatis.DefaultListGetParameter;
import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;
/**
 * <p>
 * 租户角色关联分页列表查询请求
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-01-21
 */

@Data
@ApiModel(value = "TenantRolePageGetParameter",description = "租户角色关联分页列表查询请求")
public class TenantRolePageGetParameter extends DefaultListGetParameter<TenantRoleDTO> {

    /**
     * 关联编号
     */
     @ApiModelProperty(value = "关联编号列表")
     private List<Long> trIds;
}
