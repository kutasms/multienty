package com.chia.multienty.core.parameter.user;

import com.chia.multienty.core.domain.dto.RoleDTO;
import com.chia.multienty.core.mybatis.DefaultListGetParameter;
import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel(value = "RoleListGetParameter",description = "角色列表获取请求")
public class RoleListGetParameter extends DefaultListGetParameter<RoleDTO> {
    private Long owner;
}
