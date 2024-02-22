package com.chia.multienty.core.parameter.user;

import com.chia.multienty.core.domain.dto.RoleDTO;
import com.chia.multienty.core.mybatis.DefaultListGetParameter;
import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;
/**
 * <p>
 * 角色信息分页列表查询请求
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-01-06
 */

@Data
@ApiModel(value = "RolePageGetParameter",description = "角色信息分页列表查询请求")
public class RolePageGetParameter extends DefaultListGetParameter<RoleDTO> {

    /**
     * 角色id
     */
     @ApiModelProperty(value = "角色id列表")
     private List<Long> roleIds;
}
