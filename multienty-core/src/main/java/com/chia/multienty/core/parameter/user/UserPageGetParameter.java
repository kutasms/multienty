package com.chia.multienty.core.parameter.user;

import com.chia.multienty.core.domain.dto.UserDTO;
import com.chia.multienty.core.mybatis.DefaultListGetParameter;
import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;
/**
 * <p>
 * 管理账户信息分页列表查询请求
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-01-06
 */

@Data
@ApiModel(value = "UserPageGetParameter",description = "管理账户信息分页列表查询请求")
public class UserPageGetParameter extends DefaultListGetParameter<UserDTO> {

    /**
     * 账户id
     */
     @ApiModelProperty(value = "账户id列表")
     private List<Long> userIds;
}
