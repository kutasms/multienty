package com.chia.multienty.core.parameter.user;

import com.chia.multienty.core.domain.dto.RolePermissionDTO;
import com.chia.multienty.core.mybatis.DefaultListGetParameter;
import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;
/**
 * <p>
 * 账户角色关联权限实体分页列表查询请求
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-01-06
 */

@Data
@ApiModel(value = "RolePermissionPageGetParameter",description = "账户角色关联权限实体分页列表查询请求")
public class RolePermissionPageGetParameter extends DefaultListGetParameter<RolePermissionDTO> {

    /**
     * 关联id
     */
     @ApiModelProperty(value = "关联id列表")
     private List<Long> rpIds;
}
