package com.chia.multienty.core.parameter.user;

import com.chia.multienty.core.domain.dto.UserRoleDTO;
import com.chia.multienty.core.mybatis.DefaultListGetParameter;
import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;
/**
 * <p>
 * 账户关联角色信息表分页列表查询请求
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-01-06
 */

@Data
@ApiModel(value = "UserRolePageGetParameter",description = "账户关联角色信息表分页列表查询请求")
public class UserRolePageGetParameter extends DefaultListGetParameter<UserRoleDTO> {

    /**
     * 用户角色关联编号
     */
     @ApiModelProperty(value = "用户角色关联编号列表")
     private List<Long> urIds;
}
