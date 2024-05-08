package com.chia.multienty.core.parameter.tenant;

import lombok.Data;
import com.chia.multienty.core.mybatis.DefaultListGetParameter;
import com.chia.multienty.core.domain.dto.TenantSubAccountRoleDTO;
import com.chia.multienty.core.pojo.KutaBaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotNull;
import java.util.List;
import lombok.experimental.Accessors;
/**
 * <p>
 * 租户子账号角色关联分页列表查询请求
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-03-29
 */

@Data
@ApiModel(value = "TenantSubAccountRolePageGetParameter",description = "租户子账号角色关联分页列表查询请求")
@Accessors(chain = true)
public class TenantSubAccountRolePageGetParameter extends DefaultListGetParameter<TenantSubAccountRoleDTO>{

    /**
     * 关联编号
     */
     @ApiModelProperty(value = "关联编号列表")
     private List<Long> sarIds;
    /**
     * 租户子账号编号
     */
     @ApiModelProperty(value = "租户子账号编号列表")
     private List<Long> subAccountIds;
    /**
     * 角色编号
     */
     @ApiModelProperty(value = "角色编号列表")
     private List<Long> roleIds;
}
